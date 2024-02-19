class Notice(var name: String, var text: String): MenuReadyItem() {
    fun name(): String {
        return name
    }

    override fun toString(): String {
        return name + ": $text"
    }
}