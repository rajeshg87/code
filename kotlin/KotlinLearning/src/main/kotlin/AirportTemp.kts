fun getTemparatureByCode(code: String): String = "${Math.round(Math.random() * 30 + code.count())} C";

val codes = listOf<String>("MAA", "IAD", "LAX")

val temperatures = codes.map { code -> code to getTemparatureByCode(code) }

for(temp in temperatures){
    println("Airport ${temp.first} : Temperature: ${temp.second}")
}