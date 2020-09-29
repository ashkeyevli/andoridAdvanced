package com.example.moviedbmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedbmvvm.data.model.Item
import com.example.moviedbmvvm.data.model.MovieResponse

import com.example.moviedbmvvm.data.repository.MovieListRepository

class MovieListViewModel: BaseViewModel()  {
    val movieListLive = MutableLiveData<List<Item>>()

    fun fetchMovieList() {
        dataLoading.value = true
        MovieListRepository.getInstance().getMovieList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                movieListLive.value = response?.results
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}