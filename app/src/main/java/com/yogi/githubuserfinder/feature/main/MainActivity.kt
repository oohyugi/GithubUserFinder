package com.yogi.githubuserfinder.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.yogi.githubuserfinder.R
import com.yogi.githubuserfinder.feature.home.ui.HomeFragment

/**
 * Created by oohyugi on 2020-02-01.
 * Github: https://github.com/oohyugi
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.frame_container,
                HomeFragment.newInstance(), "home"
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }


}
