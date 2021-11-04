package com.example.pokedex.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

}