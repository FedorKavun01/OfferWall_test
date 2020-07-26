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
            Log.d("mytag", "getItems: OK $items")
        } else {
            Log.d("mytag", "getItems: null array")
        }
        return items
    }

    suspend fun initNote() {
        var result: Showable? = null
        val id = items.get(notes.size).id
        Log.d(TAG, "initNote: Start")
        val note = safeApiCall(
            call = {
                api.getNote(id).await()
            }, errorMessage = ""
        )
        Log.d(TAG, "initNote: note load start $note")
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
                if (items.size > notes.size) {
                    initNote()
                }
            }
        }
        Log.d("mytag", "initNote: load finished $result")
        if (result != null && !notes.contains(result)) {
            notes.add(result)
        }

        Log.d(TAG, "initNote: notes: $notes")
    }

    suspend fun getNote(): Showable {
        if (items.size > notes.size) {
            initNote()
        }
        Log.d("mytags", "getNote: $notes")
        var result = notes.get(if (i < notes.size) i++ else {i = 0; i++})
        return result
    }
}