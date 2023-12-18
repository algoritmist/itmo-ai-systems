import kotlin.math.log2;

class C45DecisionTree : DecisionTree {
    var columnId: Int = -1
    val children: HashMap<Int, C45DecisionTree> = HashMap()
    var predictedClass: Int = -1
    var entropy: Double = 0.0

    override
    fun fit(x: List<List<Int>>, y: List<Int>) {
        _fit(x, y, labelEntropy(y))
    }

    fun _fit(x: List<List<Int>>, y: List<Int>, parentInfo: Double) {
        columnId = bestFeatureId(x, y, parentInfo)

        val labels = y.groupingBy { it }.eachCount()
        var maxSize = 0
        labels.forEach { key, value ->
            if (value > maxSize) {
                maxSize = value
                predictedClass = key
            }
        }

        if (columnId == -1) {
            return;
        }
        entropy = entropy(x, y, columnId)

        val groups: Map<Int, List<List<Int>>> = x.groupBy { row -> row[columnId] }
        if (groups.keys.size == 1) {
            val labels = y.groupingBy { it }.eachCount()
            var maxSize = 0
            labels.forEach { key, value ->
                if (value > maxSize) {
                    maxSize = value
                    predictedClass = key
                }
            }
            return;
        }

        groups.keys.forEach { key ->
            val newY = mutableListOf<Int>()
            for ((index, value) in x.withIndex()) {
                if (value[columnId] == key) {
                    newY.add(y[index])
                }
            }
            children[key] = C45DecisionTree()
            children[key]?._fit(groups[key]!!, newY, labelEntropy(newY))
        }
    }

    private fun bestFeatureId(x: List<List<Int>>, y: List<Int>, parentInfo: Double): Int {
        var index = -1
        var maxEntropy = 0.0
        for (i in 0 until x[0].size) {
            val info = entropy(x, y, i)
            //println(info)
            if (parentInfo - info > maxEntropy) {
                maxEntropy = parentInfo - info
                index = i
            }
        }
        return index
    }

    private fun labelEntropy(y: List<Int>): Double {
        val groups: Map<Int, Int> = y.groupingBy { value -> value }.eachCount()
        var entropy = 0.0
        groups.keys.forEach { key ->
            val p = groups[key]!!.toDouble() / y.size.toDouble()
            entropy -= p * log2(p)
        }
        return entropy
    }

    private fun entropy(x: List<List<Int>>, y: List<Int>, col: Int): Double {
        val groups: Map<Int, Int> = x.groupingBy { row -> row[col] }.eachCount()
        var info: Double = 0.0
        groups.keys.forEach { key ->
            val valueWeight: Double = groups[key]!!.toDouble() / x.size.toDouble()
            val newY = mutableListOf<Int>()
            for ((index, value) in x.withIndex()) {
                if (value[col] == key) {
                    newY.add(y[index])
                }
            }
            val valueEntropy = labelEntropy(newY)
            info += valueWeight * valueEntropy
        }
        return info
    }

    override
    fun predict(x: List<List<Int>>): List<Int> {
        return x.map { predictOne(it) }.toList();
    }

    private fun predictOne(x: List<Int>): Int {
        return (if (columnId != -1 && children.containsKey(x[columnId])) children.get(x[columnId])!!.predictOne(x) else predictedClass);
    }

    fun printTree(indent: Int) {
        if (columnId == -1 || children.size == 0) {
            for (i in 0 until indent) {
                print("\t");
            }
            println("Predicted class $predictedClass")
        } else {
            children.forEach { key, value ->
                for (i in 0 until indent) {
                    print("\t");
                }
                println("$columnId == $key (e == $entropy): ")
                value.printTree(indent + 1)
            }
        }
    }
}