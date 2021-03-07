package com.example.vktopicsobserver.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vktopicsobserver.R
import com.example.vktopicsobserver.ui.home.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import kotlinx.android.synthetic.main.topic_row_item.*

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ItemAdapter(homeViewModel)
        items_list.layoutManager = LinearLayoutManager(context)
        items_list.adapter = adapter
        items_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        homeViewModel.vkListData.observe(viewLifecycleOwner, Observer {
            adapter.refreshItems()
        })

        homeViewModel.fetchData(refresh = false)
    }
}