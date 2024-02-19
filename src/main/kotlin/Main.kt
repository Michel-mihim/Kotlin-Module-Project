import java.util.Scanner
import MenuManager
fun main(args: Array<String>) {
    println("Добро пожаловать в \"Архивариус\"!")
    var archiveList: MutableList<Archive> = mutableListOf()
    //индикатор выхода из программы
    var inAction: Boolean = true

    var menuManager = MenuManager()
    //запуск циклического процесса
    while (inAction) {

        menuManager.clear()

        //ввод списка для отображения

        //обновление пункта меню при выборе которого произойдет выход из программы
        var exitKey = menuManager.showArchiveList(archiveList)

        //считывание выбора пользователя
        var userInputArchive = Scanner(System.`in`).nextLine()

        //проверка на ввод корректного пункта меню
        if (menuManager.isMenuItem(userInputArchive, archiveList.size) == false) continue

        when (userInputArchive) {
            //выбор - ВЫХОД (последний пункт)
            exitKey -> inAction = false
            //выбор - СОЗДАТЬ (нулевой пункт)
            "0" -> {
                menuManager.clear()
                menuManager.addArchive(archiveList)
            }
            //выбор - ПУНКТ МЕНЮ
            else -> {
                //индикатор выхода из меню выбора заметок внутри архива
                var inDetailing: Boolean = true
                while (inDetailing) {
                    menuManager.clear()
                    val archiveSelected = archiveList[userInputArchive.toInt() - 1].name()
                    println("Выбран архив: \"$archiveSelected\"")

                    //ввод списка для отображения
                    //обновление пункта меню при выборе которого произойдет выход из меню выбора заметок
                    var backKey = menuManager.showNoticeList(archiveList[userInputArchive.toInt() - 1].noticeList)

                    //считывание выбора пользователя
                    var userInputNotice = Scanner(System.`in`).nextLine()

                    //проверка на ввод корректного пункта меню
                    if (menuManager.isMenuItem(userInputNotice, archiveList[userInputArchive.toInt() - 1].noticeList.size) == false) continue

                    when (userInputNotice) {
                        backKey -> inDetailing = false
                        "0" -> {
                            menuManager.clear()
                            menuManager.addNotice(archiveList[userInputArchive.toInt() - 1].noticeList)
                        }
                        else -> {
                            println(archiveList[userInputArchive.toInt() - 1].noticeList[userInputNotice.toInt() - 1])
                            println("\"Enter\" - назад")
                            Scanner(System.`in`).nextLine()
                        }
                    }
                }
            }
        }
    }
}