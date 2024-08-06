
    fun main() {
        var result = 0

        val properties = mutableListOf(1, 3, 4, 5, 7)

        properties.forEach { result = 8 shl (result or it) }

        println(result and 255)
    }
