import java.io.File

fun main(args: Array<String>) {
    val datafile = File("students.csv").readLines().filter { it.isNotBlank() }.map { it.split(',').map{it.toInt()}.toList() }.toList()

    val columns = datafile[0].size
    val rows = datafile.size

    val trainSize = 0.8

    val trainData = datafile.groupBy{if(it.get(columns - 1) <= 3) 0 else 1}.filter{(it.value.size * trainSize).toInt() >= 1}.
        map{it.value.subList(0, (it.value.size * trainSize).toInt())}.flatten().toList()
    println(trainData)
    val testData = datafile.groupBy{if(it.get(columns - 1) <= 3) 0 else 1}.filter{it.value.size - (it.value.size * trainSize).toInt() >= 1}.
        map{it.value.subList((it.value.size * trainSize).toInt(), it.value.size)}.flatten().toList()
    println(testData)
    val xTrain: List<List<Int>> = trainData.map{it.subList(0, columns - 1)}.toList()
    val yTrain: List<Int> = trainData.map{if(it.get(columns - 1) <= 3) 0 else 1 }.toList()

    val xTest: List<List<Int>> = testData.map{it.subList(0, columns - 1)}.toList()
    val yTest: List<Int> = testData.map{if(it.get(columns - 1) <= 3) 0 else 1 }.toList()

    val tree: C45DecisionTree = C45DecisionTree()

    tree.fit(xTrain, yTrain)
    val yPred = tree.predict(xTest)
    // println(yTest)
    // println(yPred)
    val mx = AccuracyMatrix()
    mx.score(yTest, yPred)
    println(mx)
    tree.printTree(0)
}