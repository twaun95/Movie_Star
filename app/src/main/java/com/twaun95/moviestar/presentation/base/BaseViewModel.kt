package com.twaun95.moviestar.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    val error = MutableLiveData<String>()

    protected fun startLoading() { _loading.value = true }
    protected fun stopLoading() { _loading.value = false }
}