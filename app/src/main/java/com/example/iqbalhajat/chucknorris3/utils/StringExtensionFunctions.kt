package com.example.iqbalhajat.chucknorris3.utils

fun String.containsSpace(): Boolean {
    return this.indexOf(" ") > -1
}

fun String.getIndexOfFirstNonWhitespace(): Int {
    val characters = this.toCharArray()
    for (i in 0 until this.length) {
        if (!Character.isWhitespace(characters[i])) {
            return i
        }
    }
    return 0
}

fun String.getIndexOfLastNonWhitespace(): Int {
    val characters = this.toCharArray()
    for (i in this.length-1 downTo  0) {
        if (!Character.isWhitespace(characters[i])) {
            return i
        }
    }
    return this.length-1
}
fun String.removeQuotes(): String {
    return this.replace("&quot;", "\"")
}
fun String.highlightChuckNorris(): String {
    return return this.replace("Chuck", "<b>Chuck</b>").replace("Norris","<b>Norris</b>")
}