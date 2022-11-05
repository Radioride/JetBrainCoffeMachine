package machine

abstract class CoffeCup(
    var name: String = "",
    var whater: Int = 0,
    var milk: Int = 0,
    var beans: Int = 0,
    var cost: Int = 0
){}

class coffeEspresso: CoffeCup(){
    init {
        name = "espresso"
        whater = 250
        milk = 0
        beans = 16
        cost = 4
    }
}

class coffeLatte: CoffeCup(){
    init {
        name = "latte"
        whater = 350
        milk = 75
        beans = 20
        cost = 7
    }
}

class coffeCappuccino: CoffeCup(){
    init {
        name = "cappuccino"
        whater = 200
        milk = 100
        beans = 12
        cost = 6
    }
}

class CoffeMachine(
    var coffeTypes: Array<CoffeCup> = arrayOf(coffeEspresso(), coffeLatte(), coffeCappuccino()),
    var whaterStack: Int = 400,
    var milkStack: Int = 540,
    var beansStack: Int = 120,
    var disposCapStack: Int = 9,
    var money: Int = 550
) {

    fun printStack() {
        println("The coffee machine has:")
        println("$whaterStack ml of water")
        println("$milkStack ml of milk")
        println("$beansStack g of coffee beans")
        println("$disposCapStack disposable cups")
        println("$$money of money")
    }

    fun buyCup(coffeType: Int) {
        val notEnough: String = when {
            whaterStack < coffeTypes[coffeType].whater -> "water"
            milkStack < coffeTypes[coffeType].milk -> "milk"
            beansStack < coffeTypes[coffeType].beans -> "coffee beans"
            disposCapStack < 1 -> "disposable cups"
            else -> ""
        }
        if (notEnough != "") {
            println("Sorry, not enough $notEnough!")
            return
        }
        println("I have enough resources, making you a coffee!")
        whaterStack -= coffeTypes[coffeType].whater
        milkStack -= coffeTypes[coffeType].milk
        beansStack -= coffeTypes[coffeType].beans
        disposCapStack -= 1
        money += coffeTypes[coffeType].cost
    }

    fun buy() {
        var avalibaleCoffeTypes = ""
        for(i in coffeTypes.indices) avalibaleCoffeTypes += " ${i+1} - ${coffeTypes[i].name},"
        while (true) {
            println("What do you want to buy?$avalibaleCoffeTypes back - to main menu:")
            val userInput = readln()
            if (userInput == "back") break
            val coffeType = userInput.toInt() -1
            if (coffeType in coffeTypes.indices) {
                buyCup(coffeType)
                break
            } else println("Wrong input")
        }
    }

    fun fill() {
        println("Write how many ml of water you want to add: ")
        whaterStack += readln().toInt()
        println("Write how many ml of milk you want to add:")
        milkStack += readln().toInt()
        println("Write how many grams of coffee beans you want to add: ")
        beansStack += readln().toInt()
        println("Write how many disposable cups you want to add:")
        disposCapStack += readln().toInt()
    }

    fun take() {
        println("I gave you \$$money")
        money = 0
    }
}

fun main() {
    val coffeMachine = CoffeMachine()

    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> coffeMachine.buy()
            "fill" -> coffeMachine.fill()
            "take" -> coffeMachine.take()
            "remaining" -> coffeMachine.printStack()
            "exit" -> break
            else -> println("Wrong input")
        }
    }
}
