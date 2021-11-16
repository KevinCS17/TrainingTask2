package com.example.trainingtask2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.trainingtask2.R
import com.example.trainingtask2.data.remote.ApiLogin
import com.example.trainingtask2.data.repository.LoginRepository
import com.example.trainingtask2.data.repository.Resource
import com.example.trainingtask2.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login){

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username: String = binding.etUsername.text.toString()
            val password: String = binding.etPassword.text.toString()
            viewModel.login(username,password)

//            //method1
//            lifecycleScope.launch{
//                viewModel.login(username,password).collect { response ->
//                    when(response){
//                        is Resource.Success->{
//                            Log.d("test123","Login Success ${response.Value}")
//                            Toast.makeText(requireContext(),"Login Success ${response.Value}", Toast.LENGTH_SHORT).show()
//                        }
//                        is Resource.Error->{
//                            Log.d("test123","Login Error!")
//                            Toast.makeText(requireContext(),"Login Error!", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
        }

        //method2
        viewModel.login.observe(viewLifecycleOwner, {response ->
            when(response){
                is Resource.Success->{
                    view?.findNavController()?.navigate(R.id.navigateLoginToHome)
                    Log.d("test123","Login Success ${response.Value}")
                    Toast.makeText(requireContext(),"Login Success ${response.Value}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error->{
                    Log.d("test123","Login Error!")
                    Toast.makeText(requireContext(),"Login Error!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {

    }
}