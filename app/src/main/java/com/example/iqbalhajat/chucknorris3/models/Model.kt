package com.example.iqbalhajat.chucknorris3.models


object Model {
    data class Result(val value: Value)
    data class Value(val id: Int,val joke: String)
}
