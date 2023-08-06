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
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.screenapp.R
import com.example.screenapp.util.Constants


class SplashFragment : Fragment() {

    private lateinit var pref: SharedPreferences

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

        view.postDelayed({

            if (pref.contains(Constants.ACCOUNT_ID_SHARED_PREFERENCE)
                    .and(pref.getLong(Constants.ACCOUNT_ID_SHARED_PREFERENCE, 0) != (0.toLong()))
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
        }, 2000)
    }


}