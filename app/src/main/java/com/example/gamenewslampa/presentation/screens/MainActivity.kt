package com.example.gamenewslampa.presentation.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamenewslampa.R
import com.example.gamenewslampa.databinding.ActivityMainBinding
import com.example.gamenewslampa.presentation.adapters.GameNewsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            supportActionBar!!.title = "News"
        }

        val adapter = GameNewsPagerAdapter(this)
        adapter.addFragment(StoriesFragment(), getString(R.string.stories))
        adapter.addFragment(VideoFragment(), getString(R.string.video))
        adapter.addFragment(FavouriteFragment(), getString(R.string.favourites))

        binding.pager.adapter = adapter
        binding.pager.currentItem = 0
        binding.pager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true

    }
}

