package com.example.offerwall_test

import android.util.Log

class NoteRepository(private val api : APIService) : BaseRepository() {
    var items: ArrayList<Item> = ArrayList()
    private var notes: ArrayList<Showable> = ArrayList()
    private var i = 0

    suspend fun getItems(): ArrayList<Item> {
        val itemResponse = safeApiCall(
            call = {api.getTrends().await()}, errorMessage = ""
        )
        if (itemResponse != null) {
            items.addAll(itemResponse)
        } else {
            Log.d("mytag", "getItems: null array")
        }
        return items
    }

    suspend fun initNote() {
        var result: Showable? = null
        val id = items.get(notes.size).id

        val note = safeApiCall(
            call = {
                api.getNote(id).await()
            }, errorMessage = ""
        )
        when(note?.type) {
            "text" -> {
                result = safeApiCall(
                    call = {api.getTextNote(id).await()}, errorMessage = ""
                )
            }
            "webview" -> {
                result = safeApiCall(
                    call = {api.getWebNote(id).await()}, errorMessage = ""
                )
            }
            else -> {
                items.removeAt(notes.size)
                initNote()
            }
        }
        if (result != null && !notes.contains(result)) {
            notes.add(result)
        }
    }

    suspend fun getNote(): Showable {
        if (items.size > notes.size) {
            initNote()
        }
        var result = notes.get(if (i < notes.size) i++ else {i = 0; i})
        return result
    }
}