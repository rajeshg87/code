fun fibbonacci(): Sequence<Int> {
    return generateSequence(Pair(0,1), {Pair(it.second, it.first + it.second)}).map { it.first }
}

val sum = fibbonacci().take(10).also { println(it.toList())  } .sum()
println(sum)