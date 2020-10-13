import java.lang.RuntimeException

open class Book(val name: String)
class KidsBook(name: String): Book(name)
class ProgrammingBook(name: String): Book(name)
class EducationBook(name: String): Book(name)

val books : List<Book> = listOf(KidsBook("ABC Book"), KidsBook("Rhymes Book"),
                            ProgrammingBook("Java 8"), ProgrammingBook("Programming Kotlin"),
                            EducationBook("Physcis"), EducationBook("Mathematics")
                        )

inline fun <reified T> findFirst(books: List<Book>) : T {
    val selected = books.filter { book -> book is T }
    if(selected.isEmpty()){
        throw RuntimeException("No Book Found")
    }
    return selected[0] as T
}

println("First Programming Book is ${findFirst<ProgrammingBook>(books).name}")
println("First Education Book is ${findFirst<EducationBook>(books).name}")
