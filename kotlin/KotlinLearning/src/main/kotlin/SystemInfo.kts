fun systemInfo(): String = when (val numOfCores = Runtime.getRuntime().availableProcessors()){
    1 -> "Move this machine to museum"
    in 2..16 -> "Your machine has $numOfCores cores"
    else -> "Awesome Machine with $numOfCores cores"
}

println(systemInfo())

for(i in 1 .. 5){
    println(i)
}