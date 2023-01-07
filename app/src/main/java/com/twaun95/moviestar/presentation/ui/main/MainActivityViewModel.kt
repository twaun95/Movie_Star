package com.twaun95.moviestar.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.data.local.entity.BookMarkMovieEntity
import com.twaun95.moviestar.data.repository.BookMarkMovieRepository
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.model.Result
import com.twaun95.moviestar.domain.usecase.MovieUseCase
import com.twaun95.moviestar.domain.usecase.NextPageUseCase
import com.twaun95.moviestar.presentation.base.BaseViewModel
import com.twaun95.moviestar.presentation.model.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSearchMovieUseCase: MovieUseCase,
    private val getNextPageUseCase: NextPageUseCase,
    private val bookMarkMovieRepository: BookMarkMovieRepository
) : BaseViewModel(){

    val viewMode by lazy { MutableLiveData<Mode>(Mode.SEARCH) }

    private val _movieList = MutableStateFlow(emptyList<MovieEntity>())
    val movieList: StateFlow<List<MovieEntity>>
        get() = _movieList

    private val _movieBookMarkList = MutableStateFlow(emptyList<MovieEntity>())
    val movieBookMarkList: StateFlow<List<MovieEntity>>
        get() = _movieBookMarkList

    val updatedMoviePosition by lazy { MutableLiveData<Int>() }

    // 검색
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

    // 검색 다음 페이지 조회
    fun searchNextPage() {
        viewModelScope.launch {
            startLoading()

            val result = getNextPageUseCase()
            when (result) {
                is Result.Success -> {
                    _movieList.value += result.data
                }
                is Result.Fail -> {
                    error.postValue(result.eMessage)
                }
            }

            stopLoading()
        }
    }

    // 즐겨찾기 업데이트
    fun updateBookMark(isBookMarked: Boolean, movieEntity: MovieEntity) {
        updatedMoviePosition.value = movieList.value.indexOf(movieEntity)
        if (isBookMarked) {
            _movieBookMarkList.value = _movieBookMarkList.value.minusElement(movieEntity)
        } else {
            _movieBookMarkList.value = _movieBookMarkList.value.plusElement(movieEntity)
        }
    }

    fun getBookMarkMovies() {
        Logger.d("getBookMarkMovies")
        viewModelScope.launch {
            bookMarkMovieRepository.getMovies().collect {
                Logger.d(it)
            }
        }
    }

    fun addBookMarkMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            startLoading()
            bookMarkMovieRepository.addMovie(
                BookMarkMovieEntity(
                    null,
                    title = movieEntity.title,
                    year = movieEntity.year,
                    imdbID = movieEntity.imdbID,
                    type = movieEntity.type,
                    poster = movieEntity.poster
                )
            )
            stopLoading()
        }
    }

    fun removeBookMark(movieEntity: MovieEntity) {
        viewModelScope.launch {
            bookMarkMovieRepository.removeMovie("tt0382268")
        }
    }
}