interface DecisionTree {
	fun fit(x: List<List<Int>>, y: List<Int>)
	fun predict(x: List<List<Int>>) : List<Int>
}