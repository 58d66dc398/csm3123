fun main() {
//    part 1
    var number: Int? = 10
    println(number)

    number = null
    println(number)

//    part 2
    var favoriteActor: String? = null

    if(favoriteActor != null) {
        println("The number of characters in your favorite actor's name is ${favoriteActor.length}.")
    } else {
        println("You didn't input a name.")
    }

//    part 3
    favoriteActor = "Sandra Oh"

    var lengthOfName = if (favoriteActor != null) {
        favoriteActor.length
    } else {
        0
    }

    println("The number of characters in your favorite actor's name is $lengthOfName.")

//    part 4
    favoriteActor = "Sandra Oh"

    lengthOfName = favoriteActor?.length ?: 0

    println("The number of characters in your favorite actor's name is $lengthOfName.")
}