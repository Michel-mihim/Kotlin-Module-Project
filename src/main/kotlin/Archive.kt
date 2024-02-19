import Notice

class Archive(var name: String, var noticeList: MutableList<Notice>): MenuReadyItem() {
    fun name(): String {
        return name
    }
}