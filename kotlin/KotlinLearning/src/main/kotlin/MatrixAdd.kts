val m1 = arrayOf(intArrayOf(1,2,3),
                 intArrayOf(4,5,6),
                 intArrayOf(7,8,9)
)

val m2 = arrayOf(intArrayOf(1,2,3),
    intArrayOf(4,5,6),
    intArrayOf(7,8,9)
)
val rows = m1.size
val cols = m1[0].size
val sum = Array(rows){ IntArray(cols)}

for(i in 0 until rows){
    for(j in 0 until cols){
        sum[i][j] = m1[i][j] + m2[i][j]
    }
}

println("Sum of Matrix")
for (row in sum){
    for (column in row){
        print("$column ")
    }
    println()
}
