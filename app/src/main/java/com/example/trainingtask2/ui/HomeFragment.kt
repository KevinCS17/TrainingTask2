package com.example.trainingtask2.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trainingtask2.R
import com.example.trainingtask2.constants.BundleConstants.KEY_GENDER
import com.example.trainingtask2.constants.BundleConstants.KEY_IMAGE
import com.example.trainingtask2.constants.BundleConstants.KEY_NAME
import com.example.trainingtask2.constants.BundleConstants.KEY_SPECIES
import com.example.trainingtask2.constants.BundleConstants.KEY_STATUS
import com.example.trainingtask2.data.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.navigation.NavController
import com.example.trainingtask2.databinding.FragmentHomeBinding
import com.example.trainingtask2.session.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),ClickListener {

    var cartoonAdapter: CartoonAdapter = CartoonAdapter(this)

    private val viewModel: MainViewModel by viewModels()

    lateinit var homeFragmentBinding : FragmentHomeBinding

    @Inject
    lateinit var sessionManager: SessionManager

    var timer: CountDownTimer = object : CountDownTimer(30 * 1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            //Logout
            logOut()
//            sessionManager.clear()
//            view?.findNavController()?.navigate(R.id.navigateHometoLogin)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return homeFragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        gettingData()
        homeFragmentBinding.logOutButton.setOnClickListener {
          logOut()
        }
    }

    private fun logOut(){
        sessionManager.clear()
        view?.findNavController()?.navigate(R.id.navigateHometoLogin)

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

    override fun clicked(position: Long?, item: Result?) {
        val cartoonBundle = Bundle()
        cartoonBundle.putString(KEY_NAME, item?.name ?: "")
        cartoonBundle.putString(KEY_GENDER, item?.gender ?: "")
        cartoonBundle.putString(KEY_SPECIES, item?.species ?: "")
        cartoonBundle.putString(KEY_STATUS, item?.status ?: "")
        cartoonBundle.putString(KEY_IMAGE, item?.image ?: "")
        view?.findNavController()?.navigate(R.id.navigateHomeToDetail, cartoonBundle)
    }

    override fun onResume() {
        super.onResume()
        timer.start()
    }



    override fun onPause() {
        super.onPause()
        timer.cancel()
    }
}