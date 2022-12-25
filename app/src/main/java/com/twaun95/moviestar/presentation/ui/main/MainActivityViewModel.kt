package com.twaun95.moviestar.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.model.Result
import com.twaun95.moviestar.domain.usecase.MovieUseCase
import com.twaun95.moviestar.domain.usecase.NextPageUseCase
import com.twaun95.moviestar.presentation.base.BaseViewModel
import com.twaun95.moviestar.presentation.model.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSearchMovieUseCase: MovieUseCase,
    private val getNextPageUseCase: NextPageUseCase
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

            val result = getSearchMovieUseCase(text)
            when (result) {
                is Result.Success -> {
                    _movieList.value = result.data
                }
                is Result.Fail -> {
                    error.postValue(result.eMessage)
                }
            }

            stopLoading()
        }
    }

    fun searchNextPage() {
        viewModelScope.launch {
            startLoading()

            val result = getNextPageUseCase()
            when (result) {
                is Result.Success -> {
                    _movieList.value += result.data
                }
                is Result.Fail -> {
                    error.postValue("${result.eMessage}")
                }
            }

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