package com.example.screenapp.screens

import android.content.Context
import android.content.SharedPreferences
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
import com.bumptech.glide.Glide
import com.example.screenapp.R
import com.example.screenapp.adapter.RecentMatchesAdapter
import com.example.screenapp.databinding.FragmentMainBinding
import com.example.screenapp.di.getAppComponent
import com.example.screenapp.models.AccountBaseInfoResponse
import com.example.screenapp.models.HeroResponse
import com.example.screenapp.models.RecentMatchesResponse
import com.example.screenapp.util.ApiState
import com.example.screenapp.util.Constants
import com.example.screenapp.util.HeroesList
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: RecentMatchesAdapter

    private lateinit var pref: SharedPreferences.Editor

    private var accountId: Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAppComponent().inject(this)
        init()

        adapter = RecentMatchesAdapter()
        binding.rvRecentMatches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentMatches.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.accountBaseInfoStateFlow.collectLatest {
                when (it) {
                    is ApiState.Loading -> {

                    }
                    is ApiState.Empty -> {

                    }
                    is ApiState.Failure -> {

                    }
                    is ApiState.Success<*> -> {
                        val accountInfo = it.data as AccountBaseInfoResponse
                        binding.accountName.text = accountInfo.profile!!.personaname
                        Glide.with(requireContext())
                            .load(accountInfo.profile.avatarmedium)
                            .into(binding.accountAvatar)
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.recentMatchesStateFlow.collectLatest {
                when (it) {
                    is ApiState.Loading -> {

                    }
                    is ApiState.Empty -> {


                    }
                    is ApiState.Failure -> {

                    }
                    is ApiState.Success<*> -> {
                        val recentMatchesList = it.data as List<RecentMatchesResponse>
                        //  println("Data flowed in screen : ${recentMatchesList[0]}")
                        adapter.updateRecentMatchesList(recentMatchesList)
                    }
                }
            }
        }

        binding.imageButton.setOnClickListener {

            pref.remove(Constants.ACCOUNT_ID_SHARED_PREFERENCE).apply()

            findNavController().navigate(R.id.action_mainFragment_to_loginFragment, bundleOf(),
                navOptions {
                    launchSingleTop = true
                    popUpTo(R.id.nav_graph) {
                        inclusive = true
                    }
                })

        }

    }

    private fun init() {
        accountId = requireArguments().getLong(DOTA_USER_ID)
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        adapter = RecentMatchesAdapter()
        pref =
            requireActivity().getSharedPreferences(Constants.APP_PREFERENCE, Context.MODE_PRIVATE)
                .edit()
        pref.putLong(Constants.ACCOUNT_ID_SHARED_PREFERENCE, accountId).apply()

        if (viewModel.accountBaseInfoStateFlow !is ApiState.Success<*>) {
            viewModel.getAccountBaseInfo(accountId)
        }
        if (viewModel.recentMatchesStateFlow !is ApiState.Success<*>) {
            viewModel.getRecentMatchesById(accountId)
        }
        if (HeroesList.checkForEmpty()) {
            viewModel.loadHeroes()
        }
    }


    companion object {

        const val DOTA_USER_ID = "DOTA_USER_ID"

    }


}