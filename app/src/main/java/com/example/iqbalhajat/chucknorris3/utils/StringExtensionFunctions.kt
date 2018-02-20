package com.example.iqbalhajat.chucknorris3.utils

/**
 * Extension Function: returns true if string contains one or more spaces
 *
 * @return Boolean
 */
fun String.containsSpace(): Boolean {
    return this.indexOf(" ") > -1
}

/**
 * Extension Function: returns index of first non whitespace
 *
 * @return integer
 */
fun String.getIndexOfFirstNonWhitespace(): Int {
    val characters = this.toCharArray()
    for (i in 0 until this.length) {
        if (!Character.isWhitespace(characters[i])) {
            return i
        }
    }
    return 0
}
/**
 * Extension Function: returns index of last non whitespace
 *
 * @return Boolean
 */
fun String.getIndexOfLastNonWhitespace(): Int {
    val characters = this.toCharArray()
    for (i in this.length-1 downTo  0) {
        if (!Character.isWhitespace(characters[i])) {
            return i
        }
    }
    return this.length-1
}
/**
 * Extension Function: replaces html entity &quot; with a quote
 *
 * @return string
 */
fun String.removeQuotes(): String {
    return this.replace("&quot;", "\"")
}
/**
 * Extension Function: used for highlighting the name of Chuck Norris with html bold
 *
 * @return string
 */
fun String.highlightChuckNorris(): String {
    return return this.replace("Chuck", "<b>Chuck</b>").replace("Norris","<b>Norris</b>")
}