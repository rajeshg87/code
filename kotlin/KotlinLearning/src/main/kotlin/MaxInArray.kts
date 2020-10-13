fun max(numbers: List<Int>): Int {
    var large = Int.MIN_VALUE
    for(number in numbers){
        large = if (number > large) number else large
    }
    return large
}

fun greet(name: String, msg: String = "Hello") = println("$name $msg")

val numbers = listOf(1,4,6,3,23,54,53,34)

val large = println(max(numbers))

numbers.forEach { println(it * 2) }

greet("Rajesh", "Hi")
