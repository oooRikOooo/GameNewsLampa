package com.example.gamenewslampa.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenewslampa.R
import com.example.gamenewslampa.data.models.NewsItem
import com.example.gamenewslampa.databinding.TopNewsItemBinding
import com.squareup.picasso.Picasso

class TopNewsPagerAdapter :
    PagingDataAdapter<NewsItem, TopNewsPagerAdapter.TopNewsPagerViewHolder>(NewsItemComparator) {

    class TopNewsPagerViewHolder(
        private val binding: TopNewsItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: NewsItem) {
            binding.textViewMain.text = newsItem.title
            binding.textViewLink.text = newsItem.click_url
            binding.textViewTime.text = binding.root.context.getString(R.string.time, newsItem.time)

            val imgUrl = newsItem.img?.replace("188.40.167.45:3001", "test-job.lampawork.com")

            Picasso.get().load(imgUrl).fit().centerCrop().into(binding.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsPagerViewHolder {
        val binding = TopNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TopNewsPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopNewsPagerViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}

object NewsItemComparator : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

}
