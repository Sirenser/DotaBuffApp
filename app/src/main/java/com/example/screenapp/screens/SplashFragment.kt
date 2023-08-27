package com.example.screenapp.screens

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
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
import com.example.screenapp.R
import com.example.screenapp.di.getAppComponent
import com.example.screenapp.models.RecentMatchesResponse
import com.example.screenapp.util.ApiState
import com.example.screenapp.util.Constants
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class SplashFragment : Fragment() {

    private lateinit var pref: SharedPreferences

    private lateinit var viewModel: SplashViewModel

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences(Constants.APP_PREFERENCE, MODE_PRIVATE)
        getAppComponent().inject(this)

        viewModel = ViewModelProvider(this, splashViewModelFactory)[SplashViewModel::class.java]

        viewModel.loadHeroes()

        lifecycleScope.launchWhenCreated {

            viewModel.heroesListStateFlow.collectLatest {
                if (it is ApiState.Success<*>) {
                    if (pref.contains(Constants.ACCOUNT_ID_SHARED_PREFERENCE)
                            .and(
                                pref.getLong(
                                    Constants.ACCOUNT_ID_SHARED_PREFERENCE,
                                    0
                                ) != (0.toLong())
                            )
                    ) {
                        findNavController().navigate(
                            R.id.action_splashFragment_to_mainFragment,
                            bundleOf(
                                MainFragment.DOTA_USER_ID to pref.getLong(
                                    Constants.ACCOUNT_ID_SHARED_PREFERENCE,
                                    0
                                )
                            ), navOptions {
                                launchSingleTop = true
                                popUpTo(R.id.nav_graph) {
                                    inclusive = true
                                }
                            }
                        )
                    } else {
                        findNavController().navigate(
                            R.id.action_splashFragment_to_loginFragment, bundleOf(),
                            navOptions {
                                launchSingleTop = true
                                popUpTo(R.id.nav_graph) {
                                    inclusive = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }


}