package com.example.vktopicsobserver.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vktopicsobserver.R
import com.example.vktopicsobserver.ui.home.HomeViewModel
import com.example.vktopicsobserver.ui.home.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class ToolbarFragment : Fragment(), View.OnClickListener {
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toolbar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.loader.observe(viewLifecycleOwner, Observer {
            if (it) {
                pb_loader.visibility = View.VISIBLE
                action_refresh.visibility = View.INVISIBLE
            } else {
                pb_loader.visibility = View.INVISIBLE
                action_refresh.visibility = View.VISIBLE
            }
        })

        action_refresh.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.action_refresh -> {
                    homeViewModel.fetchData(refresh = true)
                }
            }
        }
    }

}