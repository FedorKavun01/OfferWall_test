package com.example.offerwall_test

import com.google.gson.annotations.SerializedName

open class Note() {
    @SerializedName("type")
    lateinit var type: String
    constructor( type: String) : this(){
        this.type = type
    }
}

data class TextNote(val contents: String) : Note(), Showable {
    override fun getData(): String {
        return contents
    }
}

data class WebNote(val url: String) : Note(), Showable {
    override fun getData(): String {
        return url
    }
}

interface Showable {
    fun getData(): String
}