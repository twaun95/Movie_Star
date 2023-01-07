package com.twaun95.moviestar.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    val error = MutableLiveData<String>()

    fun startLoading() { _loading.value = true }
    fun stopLoading() { _loading.value = false }
}