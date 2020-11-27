package com.example.quotesapp.domain

import androidx.lifecycle.LiveData
import com.example.quotesapp.data.model.Item

interface QuoteListRepository {
    fun loadData(selectedTag: String): LiveData<List<Item>>

}