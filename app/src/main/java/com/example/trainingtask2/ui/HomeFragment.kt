package com.example.trainingtask2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trainingtask2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),ClickListener {
    companion object {
        private const val MOTION_TRANSITION_COMPLETED = 1F
        private const val MOTION_TRANSITION_INITIAL = 0F
    }

    // detect if scrolled
    private var hasMotionScrolled = false

    var cartoonAdapter: CartoonAdapter = CartoonAdapter(this)

    private val viewModel: MainViewModel by viewModels()

    private lateinit var rotate: Animation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecyclerView()

        gettingData()

    }

    // getting data from net
    private fun gettingData() {
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                cartoonAdapter.submitData(pagingData)
            }
        }

    }
    // initialize recyclerView
    private fun initRecyclerView() {
        recyclerView.apply {

            val mLayoutManager = GridLayoutManager(context, 2)

            // if it's loading or error just show as once
            mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == cartoonAdapter.itemCount) {
                        if (position % 2 == 0)
                            2
                        else
                            1
                    } else
                        1
                }
            }

            recyclerView.layoutManager = mLayoutManager
            adapter = cartoonAdapter.withMyFooter(
                footer = CartoonLoadStateAdapter { cartoonAdapter.retry() }
            )
        }
    }

    override fun clicked(value: Long?) {
//        Log.d("test123","testing masuk haha")
    }
}