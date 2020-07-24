package com.example.offerwall_test

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataGetter {
    val TAG = "mytag"
    var api = NetworkService.getService()
    var items:ArrayList<Item> = ArrayList(getItems())
    private var notes = ArrayList<Showable>()
    //todo(kostil)
    var i = 0

    //todo (end this one)
    fun getItems() : List<Item> {
        var items: List<Item>
//        api.getTrends().enqueue(object : Callback<List<Item>> {
//            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
//                Log.d(TAG, "onFailure: getItems()")
//            }
//
//            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
//                items = response.body()!!
//            }
//        })
        items = api.getTrends().execute().body()!!

        return items
    }

    fun getNote(): Showable? {
        var result: Showable? = null
        if (notes.size != items.size) {
            val id = items.get(notes.size).id

            when(api.getNote(id).execute().body()!!.type) {
                "text" -> {
                    result = api.getTextNote(id).execute().body()!!
                }
                "webview" -> {
                    result = api.getWebNote(id).execute().body()!!
                }
                else -> {
                    items.removeAt(notes.size)
                    result = getNote()
                }
            }
            if (result != null && !notes.contains(result)) {
                notes.add(result)
            }
        } else {
            result = notes.get(if (i < notes.size) i++ else {i = 0; i})
        }
        return result
    }
}