package com.ubb.album_manager.service.persistence

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UnpersistedOperationQueue(context: Context) {
    private val prefs = context.getSharedPreferences("operation_queue", Context.MODE_PRIVATE)

    private val data: ArrayList<PersistableOperation>

    init {
        val json = prefs.getString("queue", "[]")
        data = Gson().fromJson(
            json,
            object : TypeToken<MutableList<PersistableOperation>>() {}.type
        )
    }

    private fun save() {
        val json = Gson().toJson(data)
        prefs.edit().putString("queue", json).apply()
    }

    fun enqueue(operation: PersistableOperation?): Boolean {
        if (operation == null) {
            return false
        }
        data.add(data.size, operation).also { save() }
        return true
    }

    fun isEmpty(): Boolean {
        return data.isEmpty()
    }

    fun dequeue(): PersistableOperation {
        return data.removeAt(0).also { save() }
    }

}