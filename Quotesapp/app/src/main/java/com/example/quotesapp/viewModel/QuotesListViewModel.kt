package com.example.quotesapp.viewModel

import androidx.lifecycle.LiveData
import com.example.quotesapp.data.model.Item
import com.example.quotesapp.domain.GetQuoteListUseCase

class QuotesListViewModel(val getQuoteListUseCase: GetQuoteListUseCase): BaseViewModel() {
    fun fetchQuotesList(): LiveData<List<Item>> {
        return getQuoteListUseCase.getQuoteList()
    }
}