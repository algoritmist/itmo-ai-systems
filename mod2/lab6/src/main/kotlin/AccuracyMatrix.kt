class AccuracyMatrix {
	var tp: Int = 0
	var fp: Int = 0
	var fn: Int = 0	
	var tn: Int = 0	

	fun score(yPred: List<Int>, yTrue: List<Int>){
		for(i in 0 until yTrue.size){
			val x = yTrue[i]
			val y = yPred[i]
			if(x == 0 && y == 0){
				tp++;
			}
			if(x == 0 && y == 1){
				fp++;
			}
			if(x == 1 && y == 0){
				fn++;
			}
			if(x == 1 && y == 1){
				tn++;
			}
		}
	}
	
	fun accuracy(): Double{
		return (tp + tn).toDouble() / (tp + fp + fn + tn);
	}
	
	fun precision(): Double{
		return tp.toDouble() / (tp + fp)
	}
	
	fun recall(): Double{
		return tp.toDouble() / (tp + fn)
	}
    override
    fun toString(): String{
        return "Confusion matrix:\nTP: $tp, FP: $fp\nTN: $tn, FN: $fn\nPrecision: ${precision()}\nAccuracy: ${accuracy()}\nRecall: ${recall()}"
    }
}