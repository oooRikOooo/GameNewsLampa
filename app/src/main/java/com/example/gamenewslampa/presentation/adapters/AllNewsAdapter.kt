package com.example.gamenewslampa.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenewslampa.R
import com.example.gamenewslampa.data.models.NewsItem
import com.example.gamenewslampa.databinding.AllNewsItemBinding
import com.squareup.picasso.Picasso

class AllNewsAdapter :
    PagingDataAdapter<NewsItem, AllNewsAdapter.AllNewsViewHolder>(AllNewsComparator) {

    class AllNewsViewHolder(private val binding: AllNewsItemBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItem) {

            binding.textViewTitle.text = item.title
            binding.textViewLink.text = item.click_url ?: item.url
            binding.textViewTime.text = binding.root.context.getString(R.string.time, item.time)
            val imgUrl = item.img?.replace("188.40.167.45:3001", "test-job.lampawork.com")

            if (imgUrl == null) {
                binding.imageViewNews.visibility = View.GONE
            } else {
                Picasso.get().load(imgUrl.toString()).fit().centerCrop().into(binding.imageViewNews)
            }

        }
    }

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        val binding = AllNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AllNewsViewHolder(binding)
    }
}

object AllNewsComparator : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

}