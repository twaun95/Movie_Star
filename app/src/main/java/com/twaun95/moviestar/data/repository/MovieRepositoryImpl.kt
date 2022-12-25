package com.twaun95.moviestar.data.repository

import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.data.model.APIKey
import com.twaun95.moviestar.data.model.Search
import com.twaun95.moviestar.data.service.MovieService
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.model.Result
import com.twaun95.moviestar.domain.repository.MovieRepository
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val apiKey: APIKey
) : MovieRepository {

    private var currentPage = 1
    private var totalPage = 0

    // 검색 내용 조회
    override suspend fun getSearchList(search: String): Result<List<MovieEntity>> {
        val response = movieService.getMovieWithSearch(apiKey.key, "ironman")

        return try {
            if (response.isSuccessful) {

                response.body()!!.totalResults?.let { initPage(it.toInt()) }

                return response.body()!!.Search?.let {
                    Result.Success(response.body()!!.Search!!.map { Search.toMovieEntity(it) })
                }?: Result.Success(emptyList())
            } else {
                Result.Fail("영화를 검색하는데 실패했습니다.")
            }
        } catch (e: Exception) {
            Result.Fail(e.message.toString())
        }
    }

    // 다음 페이지 조회
    override suspend fun getNextPage(search: String): Result<List<MovieEntity>> {
        // 페이지 초과
        if (totalPage<currentPage) { return Result.Success(emptyList()) }

        val response = movieService.getMovieWithSearch(apiKey.key, "ironman", currentPage)

        return try {
            if (response.isSuccessful) {
                updatePage()
                return response.body()!!.Search?.let {
                    Result.Success(response.body()!!.Search!!.map { Search.toMovieEntity(it) })
                }?: Result.Success(emptyList())
            } else {
                Result.Fail("영화를 검색하는데 실패했습니다.")
            }
        } catch (e: Exception) {
            Result.Fail(e.message.toString())
        }
    }

    // 페이지 초기화
    private fun initPage(totalCount: Int) {
        totalPage = totalCount/COUNT_PER_PAGE+1
        currentPage = 2

        Logger.d("totalCount: $totalCount")
        Logger.d("totalPage: $totalPage")
        Logger.d("nextPage: $currentPage")
    }

    // 페이지 업데이트
    private fun updatePage() { currentPage += 1 }

    companion object {
        private const val COUNT_PER_PAGE = 10
    }
}