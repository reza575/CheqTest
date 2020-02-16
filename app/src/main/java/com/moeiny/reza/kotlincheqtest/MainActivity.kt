package com.moeiny.reza.kotlincheqtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.moeiny.reza.kotlincheqtest.fragment.ExpenseFragment
import com.moeiny.reza.kotlincheqtest.fragment.HomeFragment
import com.moeiny.reza.kotlincheqtest.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var img_home: ImageView
    lateinit var img_expense: ImageView
    lateinit var img_profile: ImageView
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupVews()

        img_home.setOnClickListener {
            defaultViews()
            img_home.setImageResource(R.drawable.active_home)
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.rel_main_fragmentContainer, HomeFragment())
            fragmentTransaction.commit()
        }

        img_expense.setOnClickListener {
            defaultViews()
            img_expense.setImageResource(R.drawable.active_expense)
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.rel_main_fragmentContainer, ExpenseFragment())
            fragmentTransaction.commit()
        }

        img_profile.setOnClickListener {
            defaultViews()
            img_profile.setImageResource(R.drawable.active_profile)
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.rel_main_fragmentContainer, ProfileFragment())
            fragmentTransaction.commit()
        }
    }

    private fun setupVews() {
        img_home = findViewById(R.id.img_navigation_home) as ImageView
        img_expense = findViewById(R.id.img_navigation_expense) as ImageView
        img_profile = findViewById(R.id.img_navigation_profile) as ImageView
    }

    private fun defaultViews() {
        img_home.setImageResource(R.drawable.passive_home)
        img_expense.setImageResource(R.drawable.passive_expense)
        img_profile.setImageResource(R.drawable.passive_profile)
    }
}
