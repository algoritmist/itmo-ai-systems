import java.io.InputStream

class CSVReader {
	fun read(inputStream: InputStream, withHeader: Boolean): List<List<Int>> {
		val reader = inputStream.bufferedReader()
		if(withHeader){
			reader.readLine()
		}
		return reader.lineSequence().filter{it.isNotBlank()}.map{
			it.split(',').map{it.toInt()}
		}.toList()
	}
}