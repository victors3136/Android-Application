package com.ubb.album_manager.service.persistence

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ubb.album_manager.service.persistence.remote.NetworkUtils

class NetworkStateReceiver(private val onConnected: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = NetworkUtils(context).isOnline()
        if (isConnected) {
            onConnected()
        }
    }
}