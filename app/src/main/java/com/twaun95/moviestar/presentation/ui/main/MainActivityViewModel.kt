package com.twaun95.moviestar.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.presentation.base.BaseViewModel
import com.twaun95.moviestar.presentation.model.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel(){

    val visibleTest by lazy { MutableLiveData<Boolean>(true) }
    val modeTest by lazy { MutableLiveData<Mode>(Mode.SEARCH) }


    fun search(text: String) {
        Logger.d("Search $text")
    }
}