fun lastName(name: String?): String {
    if(name === "Rajesh"){
        return "Kannan"
    }
    return name?.reversed()?.toUpperCase() ?: "Joker"
}

var name: String? = "Rajesh"
println("Last name for ${name}  is ${lastName(name)}")