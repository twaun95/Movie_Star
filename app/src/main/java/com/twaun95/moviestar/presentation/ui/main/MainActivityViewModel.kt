package com.twaun95.moviestar.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.usecase.MovieUseCase
import com.twaun95.moviestar.presentation.base.BaseViewModel
import com.twaun95.moviestar.presentation.model.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getMovieUseCase: MovieUseCase
) : BaseViewModel(){

    val viewMode by lazy { MutableLiveData<Mode>(Mode.SEARCH) }

    private val _movieList = MutableStateFlow(emptyList<MovieEntity>())
    val movieList: StateFlow<List<MovieEntity>>
        get() = _movieList

    private val _movieBookMarkList = MutableStateFlow(emptyList<MovieEntity>())
    val movieBookMarkList: StateFlow<List<MovieEntity>>
        get() = _movieBookMarkList

    val updatedMoviePosition by lazy { MutableLiveData<Int>() }

    fun searchMovie(text: String) {
        viewModelScope.launch {
            startLoading()

            val result = getMovieUseCase("ironman", 1)
//            _movieList.value = emptyList()
            _movieList.value = result

//            Logger.d(_movieList.value)

            stopLoading()
        }
    }


    fun updateBookMark(isBookMarked: Boolean, movieEntity: MovieEntity) {
        updatedMoviePosition.value = movieList.value.indexOf(movieEntity)
        if (isBookMarked) {
            _movieBookMarkList.value = _movieBookMarkList.value.minusElement(movieEntity)
        } else {
            _movieBookMarkList.value = _movieBookMarkList.value.plusElement(movieEntity)
        }
    }
}