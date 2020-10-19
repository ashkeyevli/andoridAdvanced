package com.example.quotesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.quotesapp.databinding.QuotesListFragmentBinding
import com.example.quotesapp.view.adapter.QuotesListAdapter
import com.example.quotesapp.viewModel.QuotesListViewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.quotes_list_fragment.*

class QuotesListFragment : Fragment(){
    private lateinit var viewDataBinding: QuotesListFragmentBinding
    private lateinit var adapter: QuotesListAdapter
    private val QuotesListViewModel:QuotesListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = QuotesListFragmentBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(viewLifecycleOwner)
        }
        viewDataBinding.viewModel = QuotesListViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewModel?.fetchQuotesList()
        setupAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewDataBinding.viewModel?.fetchQuotesList()?.observe(viewLifecycleOwner, Observer {
            adapter.updateQuoteList(it)


        })

    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewModel
        if (viewModel != null) {
            adapter = QuotesListAdapter(viewDataBinding.viewModel!!, activity)
            val layoutManager = LinearLayoutManager(activity)
            quotes_list_rv.layoutManager = layoutManager
            quotes_list_rv.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    layoutManager.orientation
                )
            )
            quotes_list_rv.adapter = adapter
        }
    }
}
