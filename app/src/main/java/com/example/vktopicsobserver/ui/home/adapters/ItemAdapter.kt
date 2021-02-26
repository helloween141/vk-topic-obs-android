package com.example.vktopicsobserver.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vktopicsobserver.R
import com.example.vktopicsobserver.ui.home.HomeViewModel
import com.example.vktopicsobserver.ui.home.models.CommentCellModel
import com.example.vktopicsobserver.ui.home.models.TopicCellModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_row_item.view.*
import kotlinx.android.synthetic.main.topic_row_item.view.*


class ItemAdapter(private val homeViewModel: HomeViewModel) :
    RecyclerView.Adapter<ItemAdapter.BaseViewHolder<*>>() {

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    companion object {
        private const val TOPIC_VIEW_TYPE = 0
        private const val COMMENT_VIEW_TYPE = 1
    }

    private var adapterDataList: List<Any> = emptyList()

    inner class TopicViewHolder(itemView: View) : BaseViewHolder<TopicCellModel>(itemView) {
        override fun bind(item: TopicCellModel) {
            itemView.tv_topicTitle.text = item.title
            Picasso.get().load(item.groupPhoto).resize(150, 150).into(itemView.img_group);
        }
    }

    inner class CommentViewHolder(itemView: View) : BaseViewHolder<CommentCellModel>(itemView) {
        override fun bind(item: CommentCellModel) {
            itemView.tv_commentText.text = item.text
            itemView.tv_commentAuthor.text = item.profile.name
            itemView.tv_commentDate.text = item.date
            Picasso.get().load(item.profile.photo).resize(150, 150).into(itemView.imgComment);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TOPIC_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.topic_row_item, parent, false)
                TopicViewHolder(view)
            }
            COMMENT_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_row_item, parent, false)
                CommentViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return adapterDataList.size
    }

    fun refreshItems() {
        this.adapterDataList = homeViewModel.vkListData.value!!
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        val comparable = adapterDataList[position]
        return when (comparable) {
            is TopicCellModel -> TOPIC_VIEW_TYPE
            is CommentCellModel  -> COMMENT_VIEW_TYPE
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = adapterDataList[position]
        when (holder) {
            is CommentViewHolder -> holder.bind(element as CommentCellModel)
            is TopicViewHolder -> holder.bind(element as TopicCellModel)
            else -> throw IllegalArgumentException()
        }
    }

}