import java.lang.RuntimeException

fun tryCatchExpr(blowUp: Boolean): String {
    try{
        if(blowUp){
            throw RuntimeException("Runtime Fail")
        }
        return "Pass"
    }catch (ex: Exception){
        return "Fail ${ex.localizedMessage}"
    }
}

tryCatchExpr(true)