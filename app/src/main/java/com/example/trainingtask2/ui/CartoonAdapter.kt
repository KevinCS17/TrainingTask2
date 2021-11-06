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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.trainingtask2.R
import com.example.trainingtask2.data.model.Result
import kotlinx.android.synthetic.main.cartoon_item.view.*
import javax.inject.Inject

class CartoonAdapter @Inject constructor(private val listener: ClickListener):
    PagingDataAdapter<Result, CartoonAdapter.ViewHolder>(CartoonDiff){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.clicked(getItem(position)?.id)
            Log.d("test123", getItem(position)!!.id.toString())
        }

        Glide.with(holder.itemView)
            .load(getItem(position)?.image)
            .transform(RoundedCorners(30))
            .into(holder.itemView.cartoon_poster)

        holder.itemView.cartoonName.text = getItem(position)?.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cartoon_item, parent, false)
        )
    }

    object CartoonDiff : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem
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