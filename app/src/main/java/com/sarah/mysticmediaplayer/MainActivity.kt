package com.sarah.mysticmediaplayer

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_ticket.view.*

class MainActivity : AppCompatActivity() {
    lateinit var tabViewPager:ViewPager
    lateinit var tabLayout:TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = tabs_main
        tabViewPager = tabviewPager

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        tabviewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(tabviewPager)


    }
}
