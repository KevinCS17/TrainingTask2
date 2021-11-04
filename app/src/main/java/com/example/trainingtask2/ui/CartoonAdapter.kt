package com.example.trainingtask2.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trainingtask2.R
import com.example.trainingtask2.data.model.CartoonModel
import com.example.trainingtask2.data.model.CartoonResultModel
import com.example.trainingtask2.data.model.Result
import kotlinx.android.synthetic.main.cartoon_item.view.*
import javax.inject.Inject

class CartoonAdapter @Inject constructor(private val listener: ClickListener):
    PagingDataAdapter<CartoonModel, CartoonAdapter.ViewHolder>(CartoonDiff){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.clicked(getItem(position)?.results?.get(position)?.id)
        }

        Glide.with(holder.itemView).load(getItem(position)?.results?.get(position)?.image)
            .into(holder.itemView.cartoon_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cartoon_item, parent, false)
        )
    }

    object CartoonDiff : DiffUtil.ItemCallback<CartoonModel>() {
        override fun areItemsTheSame(oldItem: CartoonModel, newItem: CartoonModel): Boolean =
            oldItem.results[0].id == newItem.results[0].id

        override fun areContentsTheSame(oldItem: CartoonModel, newItem: CartoonModel): Boolean =
            newItem == oldItem
    }
    fun withMyFooter(
        footer: LoadStateAdapter<*>
    ): ConcatAdapter {
        addLoadStateListener { loadStates ->
            footer.loadState = when (loadStates.refresh) {
                is LoadState.NotLoading -> loadStates.append
                else -> loadStates.refresh
            }
        }
        return ConcatAdapter(this, footer)
    }

}