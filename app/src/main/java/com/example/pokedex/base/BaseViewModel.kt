package com.example.pokedex.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val context by lazy { application.applicationContext }
    protected fun getString(id: Int): String = context.resources.getString(id)
}