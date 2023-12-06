import java.io.File

fun main() {
    val inputTask1 = readFileAsList("input.txt")
    val inputTask2 = readFileAsList("input2.txt")

    task1(inputTask1)
    task2(inputTask2)


}

fun task1(inputFile: List<String>) {
    var digits: Int? = 0

    inputFile.forEach {
        val line = "${it.find { it.isDigit() }}${it.findLast { it.isDigit() }}".toInt()
        digits = digits?.plus(line)
    }

    println("Task A: $digits")
}

fun task2(inputLine: List<String>) {
    var result = 0
    inputLine.forEach { it ->
        val line = if (it.length >= 5) {
            task2Recur(it, 0)
        } else {
            it
        }
        val digits = "${line.find { it.isDigit() }}${line.findLast { it.isDigit() }}".toInt()
        result = result.plus(digits)
    }

    println("Task B: $result")
}

fun task2Recur(inputLine: String, position: Int): String {
    val numbers = mapOf(
        "zero" to "0",
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    var input = inputLine.slice(position..position+4)
    numbers.forEach {
        input = input.replace(it.key, it.value)
    }

    if (position+5 >= inputLine.length) {
        return input
    }
    return input + task2Recur(inputLine, position+1)
}

fun readFileAsList(fileName: String): List<String>
    = File(ClassLoader.getSystemResource(fileName).file).useLines { it.toList() }