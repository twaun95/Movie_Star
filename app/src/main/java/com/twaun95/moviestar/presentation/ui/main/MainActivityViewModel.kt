package com.twaun95.moviestar.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.domain.usecase.MovieUseCase
import com.twaun95.moviestar.presentation.base.BaseViewModel
import com.twaun95.moviestar.presentation.model.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getMovieUseCase: MovieUseCase
) : BaseViewModel(){

    val visibleTest by lazy { MutableLiveData<Boolean>(true) }
    val modeTest by lazy { MutableLiveData<Mode>(Mode.SEARCH) }


    fun searchMovie(text: String) {
        viewModelScope.launch {
            startLoading()

            val result = getMovieUseCase(text, 1)
            Logger.d(result)

            stopLoading()
        }
    }
}