package com.example.vktopicsobserver.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.vktopicsobserver.R
import com.example.vktopicsobserver.settings.SettingsObject
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), View.OnClickListener {
    private val settingsViewModel: SettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        settingsViewModel.settingsData.observe(viewLifecycleOwner, Observer {
            cb_sort_topics.isChecked = (it.topicsSort != "DESC")
            cb_sort_comments.isChecked = (it.commentsSort != "DESC")
            et_max_comments_per_topic.setText(it.commentsPerTopic.toString())
        })

        settingsViewModel.resultMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        btn_save_settings.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.btn_save_settings -> {
                    val maxComments = et_max_comments_per_topic.text.toString().toIntOrNull()?.let {
                        Math.abs(
                            it
                        )
                    }

                    SettingsObject.apply {
                        topicsSort = if(cb_sort_topics.isChecked) "ASC" else "DESC"
                        commentsSort = if(cb_sort_comments.isChecked) "ASC" else "DESC"
                        commentsPerTopic = maxComments ?: commentsPerTopic
                    }
                    settingsViewModel.save()
                }
            }
        }
    }

}