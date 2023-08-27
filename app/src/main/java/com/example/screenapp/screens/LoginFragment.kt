package com.example.screenapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screenapp.R
import com.example.screenapp.adapter.AccountSearchAdapter
import com.example.screenapp.databinding.FragmentLoginBinding
import com.example.screenapp.di.getAppComponent
import com.example.screenapp.util.ApiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.screenapp.models.AccountSearchResponse as AccountSearchResponse


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var viewModel: LoginViewModel

    private lateinit var adapter: AccountSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]
        adapter = AccountSearchAdapter {
            findNavController().navigate(
                R.id.action_loginFragment_to_mainFragment,
                bundleOf(MainFragment.DOTA_USER_ID to it),
                navOptions {
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph) {
                        inclusive = true
                    }
                }
            )
        }

        with(binding) {
            rvAccountsSearch.adapter = adapter
            rvAccountsSearch.layoutManager = LinearLayoutManager(context)

            loginButton.setOnClickListener {
                if (etLogin.text.isNullOrEmpty()) {
                    binding.tilLogin.error = "Enter the text!"
                } else {
                    viewModel.getAccountSearchResponse(etLogin.text.toString())
                }
            }

            lifecycleScope.launch {
                viewModel.accountInfoStateFlow.collectLatest {
                    when (it) {
                        is ApiState.Loading -> {
                            binding.pbAccountsSearch.visibility = View.VISIBLE
                            binding.rvAccountsSearch.visibility = View.GONE
                        }
                        is ApiState.Empty -> {
                            binding.pbAccountsSearch.visibility = View.GONE
                            binding.rvAccountsSearch.visibility = View.GONE
                        }
                        is ApiState.Failure -> {
                            binding.tilLogin.error = it.msg
                            binding.pbAccountsSearch.visibility = View.GONE
                            binding.rvAccountsSearch.visibility = View.GONE
                        }
                        is ApiState.Success<*> -> {

                            binding.tilLogin.error = null
                            val list = it.data as List<AccountSearchResponse>
                            adapter.updateAccountSearchList(list)
                            binding.pbAccountsSearch.visibility = View.GONE
                            binding.rvAccountsSearch.visibility = View.VISIBLE

                        }
                    }
                }
            }
        }
    }
}