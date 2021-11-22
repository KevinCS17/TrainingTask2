package com.example.trainingtask2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.trainingtask2.IdleTimer
import com.example.trainingtask2.R
import com.example.trainingtask2.data.repository.Resource
import com.example.trainingtask2.databinding.FragmentLoginBinding
import com.example.trainingtask2.session.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun isValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(et_username.text).matches()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner){
            activity?.finish()
        }

        binding.btnLogin.setOnClickListener {
            if (isValid()) {
                handleLogin(it)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Email not valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

//        //method2
//        viewModel.login.observe(viewLifecycleOwner, {response ->
//            when(response){
//                is Resource.Success->{
//                    view?.findNavController()?.navigate(R.id.navigateLoginToHome)
//                    Log.d("test123","Login Success ${response.Value}")
//                    Toast.makeText(requireContext(),"Login Success ${response.Value}", Toast.LENGTH_SHORT).show()
//                }
//                is Resource.Error->{
//                    Log.d("test123","Login Error!")
//                    Toast.makeText(requireContext(),"Login Error!", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
    }

    fun handleLogin(view: View) {

        val username: String = binding.etUsername.text.toString()
        val password: String = binding.etPassword.text.toString()
        viewModel.login(username, password)

        //method1
        lifecycleScope.launch {
            viewModel.login(username, password).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        view.findNavController().navigate(R.id.navigateLoginToHome)
                        sessionManager.setToken(response.Value.token)
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Login Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}