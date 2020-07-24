package com.example.offerwall_test

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NoteViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : NoteRepository = NoteRepository(NetworkService.getService())

    fun fetchItems() {
        scope.launch{
            async { repository.getItems() }.await()
        }
    }

    fun getStarted(): Unit {
        scope.launch {
            async {
                fetchItems()
                repository.initNote() }.await()
        }
    }

    fun fetchFragment(): Fragment {
        var note: Showable? = null
        var fragment: Fragment? = null
        scope.launch {
            runBlocking { note = repository.getNote()
                when(note) {
                    is WebNote -> {
                        fragment = WebFragment(note as WebNote)
                    }
                    is TextNote -> {
                        fragment =  TextFragment(note as TextNote)
                    }
                }}
        }

        return fragment!!
    }

}