package com.geekforgeek.netfix_retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RetrofitRepo(val retrofitHelper: RetrofitHelper) {
    var onlyOnNetflix = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    var blockBaster = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    var trending = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    var watchAgain = MutableStateFlow<ArrayList<MovieModel>>(arrayListOf())
    fun getData() {
        GlobalScope.launch(Dispatchers.IO) {
            val only = arrayListOf<MovieModel>()
            val block = arrayListOf<MovieModel>()
            val tre = arrayListOf<MovieModel>()
            val watch = arrayListOf<MovieModel>()
            val moviesList = retrofitHelper.movieList().body() ?: arrayListOf()
            for (i in 0..10) {
                only.add(moviesList[i])
            }
            onlyOnNetflix.value = only
            for (i in 11..20) {
                block.add(moviesList[i])
            }
            blockBaster.value = block

            for (i in 21..30) {
                tre.add(moviesList[i])
            }
            trending.value = tre

            for (i in 31..40) {
                watch.add(moviesList[i])
            }
            watchAgain.value = watch
        }

    }
}