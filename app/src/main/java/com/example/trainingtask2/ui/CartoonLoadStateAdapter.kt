package com.example.trainingtask2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingtask2.R
import kotlinx.android.synthetic.main.load_state_view.view.*

class CartoonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CartoonLoadStateAdapter.LoadStateViewHolder>() {


    class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.itemView.load_state_progress
        val btnRetry = holder.itemView.load_state_retry
        val txtErrorMessage = holder.itemView.load_state_errorMessage

        btnRetry.isVisible = loadState !is LoadState.Loading
        txtErrorMessage.isVisible = loadState !is LoadState.Loading
        progress.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error) {
            txtErrorMessage.text = loadState.error.message
        }

        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_view, parent, false)
        )

    }


}
