val hourDeclination: Array<String> = arrayOf("часов", "час", "часа")
val minuteDeclination: Array<String> = arrayOf("минут", "минуту", "минуты")

fun main() {

    agoToText(59)
    agoToText(60)
    agoToText(60 * 11)
    agoToText(60 * 4)
    agoToText(60 * 60)
    agoToText(60 * 60 * 7)
    agoToText(60 * 60* 23)
    agoToText(60 * 60 * 25)
    agoToText(60 * 60 * 24 * 2)
    agoToText(60 * 60 * 24 * 24)

}

fun timeDimensions(numberTime: String): Int {
    when (numberTime) {
        "second" -> return 60
        "minute" -> return 60 * 60
        "day" -> return 60 * 60 * 24
    }
    return 0
} // Вычисление размера времени (секунда, минута, час, сутки...)

fun declination(time: Int, timeSize: String): String {
    when (timeSize) {
        // минуты
        "second" -> {
            return ending(time, minuteDeclination, timeSize)
        }
        // Часы
        "minute" -> {
            return ending(time, hourDeclination, timeSize)
        }
    }
    return "trouble"
} // Окончание
fun ending(time: Int, name: Array<String>, timeSize: String): String {
    val amountOfTime = (time / timeDimensions(timeSize))
    val ten = amountOfTime % 100
    val units = amountOfTime % 10
    return when{
        ten in 10..20 -> "$amountOfTime ${name[0]}"
        units == 1 -> "$amountOfTime ${name[1]}"
        units in 2..4 -> "$amountOfTime ${name[2]}"
        else -> "$amountOfTime ${name[0]}"
    }
}

fun agoToText(time: Int) {
    when (time) {
//    Если количество секунд от 0 до 60, використовуйте вариант с только что.
        in Int.MIN_VALUE until (timeDimensions("second")) ->
            println("Был(а) в сети только что")
//    Если количество секунд от 61 до 60 * 60 (один час), вариант с x минут назад.
        in timeDimensions("second") until timeDimensions("minute") -> {
            println("Был(а) в сети ${declination(time,"second")} назад") // TODO доделать вывод
        }
//    Если количество секунд от 60 * 60 + 1 до 24 * 60 * 60 (сутки), вариант с x часов назад.
        in (timeDimensions("minute")) until timeDimensions("day") -> {
            println("Был(а) в сети ${declination(time,"minute")} назад") //TODO доделать вывод
        }
//    Если количество секунд от суток до двух, вариант вчера.
        in timeDimensions("day") until timeDimensions("day") * 2 ->
            println("Был(а) в сети вчера")
//    Если количество секунд от двух суток до трех, вариант позавчера.
        in timeDimensions("day") * 2 until timeDimensions("day") * 3 ->
            println("Был(а) в сети позавчера")
//    Если количество секунд больше трех суток, вариант давно.
        in timeDimensions("day") * 3..Int.MAX_VALUE ->
            println("Был(а) в сети давно")
    } //Проверка времени и сборка ответа
} //Окончательный ответ
