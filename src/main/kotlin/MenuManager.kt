import java.util.Scanner
import Archive
import Notice

class MenuManager () {
    companion object {
        const val MENU_ARCHIVE_CREATE = "0. Создать новый архив"
        const val MENU_NOTICE_CREATE = "0. Создать новую заметку"
        const val ARCHIVE_NAME_REQUEST = "Введите название архива!"
        const val NOTICE_NAME_REQUEST = "Введите название заметки!"
        const val NOTICE_TEXT_REQUEST = "Введите текст заметки!"
        const val EMPTY_STRING_WARNING = "Ошибка!\nВведена пустая строка."
        const val INCORRECT_MENU_ITEM_FORMAT_WARNING = "Неправильный ввод!\nВведите номер пункта меню!"
        const val NONEXISTENT_MENU_ITEM_WARNING = "Такого пункта меню не существует!\nВведите номер пункта меню!"
        const val APP_ESCAPE = "Выйти"
        const val STEP_BACK = "Назад"
    }

    // Уважаемый код-ревьюер! Подскажи пожалуйста что тут использовать  чтобы написать единый метод
    // для обработки любого наследуемого объекта в зависимости от типа.
    // Тут стирание типов происходит. Нужно дженерик использовать вроде...

    fun showArchiveList(elements: MutableList<Archive>): String {
        var counter: Int = 1
        println(MENU_ARCHIVE_CREATE)
        for(element in elements) {
            println("$counter. " + element.name())
            counter++
        }
        println("$counter. $APP_ESCAPE")
        return counter.toString()
    }

    fun showNoticeList(elements: MutableList<Notice>): String {
        var counter: Int = 1
        println(MENU_NOTICE_CREATE)
        for(element in elements) {
            println("$counter. " + element.name())
            counter++
        }
        println("$counter. $STEP_BACK")
        return counter.toString()
    }

    fun addArchive(elements: MutableList<Archive>): MutableList<Archive> {
        println(ARCHIVE_NAME_REQUEST)
        val newElementName = Scanner(System.`in`).nextLine()

        //проверка на введение пустой строки
        if (newElementName == "") {
            println(EMPTY_STRING_WARNING)
            return elements
        }

        //если предыдущие проверки успешно пройдены, то дошли до шага добавления пункта в список
        var newArchive = Archive(newElementName, mutableListOf())
        elements.add(newArchive)
        return elements
    }

    fun addNotice(elements: MutableList<Notice>): MutableList<Notice> {
        println(NOTICE_NAME_REQUEST)
        val newElementName = Scanner(System.`in`).nextLine()

        //проверка на введение пустой строки
        if (newElementName == "") {
            println(EMPTY_STRING_WARNING)
            return elements
        }

        //если предыдущие проверки успешно пройдены, то дошли до шага добавления пункта в список
        var text: String = ""
        while (text == "") {
            println(NOTICE_TEXT_REQUEST)
            text = Scanner(System.`in`).nextLine()
        }
        var newNotice = Notice(newElementName, text)
        elements.add(newNotice)
        return elements
    }

    fun clear() {
        var space: String = ""
        for (i in 1..30)
            space = space + "-"
        println(space)
    }

    fun isMenuItem(item: String, itemCount: Int): Boolean {

        // проверка ввода, что он является числом
        try {
            val parsedInt = item.toInt()
            if (parsedInt in 0..itemCount+1) return true
        } catch (nfe: NumberFormatException) {
            println(INCORRECT_MENU_ITEM_FORMAT_WARNING)
            return false
        }
        println(NONEXISTENT_MENU_ITEM_WARNING)
        return false
    }
}