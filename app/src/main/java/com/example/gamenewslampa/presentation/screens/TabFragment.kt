package com.example.gamenewslampa.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamenewslampa.databinding.FragmentTabBinding
import com.example.gamenewslampa.presentation.NewsViewModel
import com.example.gamenewslampa.presentation.NewsViewModelFactory
import com.example.gamenewslampa.presentation.adapters.AllNewsAdapter
import com.example.gamenewslampa.presentation.adapters.TopNewsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

abstract class TabFragment : Fragment() {

    abstract val value: String

    private lateinit var binding: FragmentTabBinding

    private val viewModel by viewModels<NewsViewModel> { NewsViewModelFactory() }

    private val allNewsAdapter = AllNewsAdapter()
    private val topNewsPagerAdapter = TopNewsPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTabBinding.inflate(layoutInflater, container, false)

        setAdapters()
        setData()

        return binding.root
    }

    private fun setAdapters() = with(binding) {
        viewPagerTab.adapter = topNewsPagerAdapter

        TabLayoutMediator(tabLayout, viewPagerTab) { tab, position ->
        }.attach()

        allNewsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
            adapter = allNewsAdapter
        }
    }

    private fun setData() {
        viewModel.news.observe(viewLifecycleOwner) { pagingData ->

            allNewsAdapter.submitData(lifecycle, pagingData.filter {
                it.type == value
            })

            topNewsPagerAdapter.submitData(lifecycle, pagingData.filter {
                it.type == value && it.top == "1"
            })

        }
    }
}

class StoriesFragment : TabFragment() {
    override val value: String = "strories"
}

class VideoFragment : TabFragment() {
    override val value: String = "video"
}

class FavouriteFragment : TabFragment() {
    override val value: String = "favourites"
}