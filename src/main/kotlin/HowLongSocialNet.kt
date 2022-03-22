import kotlin.math.roundToInt

const val HOUR = 60 * 60
const val DAY = 60 * 60 * 24

fun main() {
    val numberOfSeconds = 6555801
    val howLong = agoToText(numberOfSeconds)
    print("был(а) в сети $howLong")
}

fun parameterNumber(numberOfSeconds: Int): Int {
    var convertFromSeconds = 0
    val convertToMinute = (numberOfSeconds / 60.0).roundToInt()
    val convertToHours = (numberOfSeconds / (HOUR * 1.0)).roundToInt()

    if (numberOfSeconds > 60 && numberOfSeconds <= HOUR) {
        convertFromSeconds = convertToMinute
    } else if (numberOfSeconds > HOUR && numberOfSeconds <= DAY) {
        convertFromSeconds = convertToHours
    }
    return when {
        (convertFromSeconds == 1 || convertFromSeconds % 10 == 1) && convertFromSeconds != 11 -> 1
        (convertFromSeconds >= 2 && convertFromSeconds <= 4) || (convertFromSeconds % 10 >= 2 && convertFromSeconds % 10 <= 4) -> 2
        else -> 3
    }
}

fun wordEndHour(numberOfSeconds: Int): String {
    val choice = parameterNumber(numberOfSeconds)
    return when (choice) {
        1 -> "час"
        2 -> "часа"
        else -> "часов"
    }
}

fun wordEndMinute(numberOfSeconds: Int): String {
    val choice = parameterNumber(numberOfSeconds)
    return when (choice) {
        1 -> "минуту"
        2 -> "минуты"
        else -> "минут"
    }
}

fun agoToText(numberOfSeconds: Int): String {
    val minutes = wordEndMinute(numberOfSeconds)
    val hours = wordEndHour(numberOfSeconds)
    return when {
        numberOfSeconds <= 60 -> "только что"
        numberOfSeconds > 60 && numberOfSeconds <= HOUR -> "${numberOfSeconds / 60} $minutes назад"
        numberOfSeconds > HOUR && numberOfSeconds <= DAY -> "${numberOfSeconds / HOUR} $hours назад"
        numberOfSeconds > DAY && numberOfSeconds <= 2 * DAY -> "сегодня"
        numberOfSeconds > 2 * DAY && numberOfSeconds <= 3 * DAY -> "вчера"
        else -> "давно"
    }
}
