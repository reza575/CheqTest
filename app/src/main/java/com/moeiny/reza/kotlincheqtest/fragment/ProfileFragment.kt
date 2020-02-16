package com.moeiny.reza.kotlincheqtest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moeiny.reza.kotlincheqtest.R

class ProfileFragment : Fragment() {

    lateinit var myView: View

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        myView = inflater.inflate(R.layout.fragment_profile, container, false)

        return myView
    }
}