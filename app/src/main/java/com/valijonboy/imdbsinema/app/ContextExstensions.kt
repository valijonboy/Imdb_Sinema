package com.valijonboy.imdbsinema.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable

inline fun <reified T : Activity> Context.newIntent(key: String, value: Serializable, key2: String, value2: String?): Intent =
    Intent(this, T::class.java).putExtra(key, value).putExtra(key2, value2)

inline fun <reified T : Activity> Context.startActivity(key: String, value: Serializable, key2: String, value2: String?) =
    this.startActivity(newIntent<T>(key, value, key2, value2))