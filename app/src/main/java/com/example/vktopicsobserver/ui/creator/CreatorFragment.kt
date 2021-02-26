package com.example.vktopicsobserver.ui.creator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vktopicsobserver.R
import kotlinx.android.synthetic.main.fragment_creator.*

class CreatorFragment : Fragment() {
    private lateinit var creatorViewModel: CreatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        creatorViewModel = ViewModelProvider(this).get(CreatorViewModel::class.java)

        btnAddTopic.setOnClickListener {
            val topicUrl = etTopicUrl.text.toString()

            if (topicUrl.isNotEmpty()) {
                creatorViewModel.addTopic(topicUrl)
                etTopicUrl.setText("")
            }
        }

        creatorViewModel.resultMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}