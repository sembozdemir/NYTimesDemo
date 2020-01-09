package com.sembozdemir.nytimesdemo.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sembozdemir.nytimesdemo.R
import com.sembozdemir.nytimesdemo.core.extensions.autoNotify
import com.sembozdemir.nytimesdemo.core.extensions.inflate

class NewsAdapter(
    private var items: List<NewsItem> = emptyList(),
    private val onItemClick: (item: NewsItem) -> Unit
) : RecyclerView.Adapter<NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = parent.inflate(R.layout.list_item_news)
        val viewHolder = NewsItemViewHolder(itemView)
        itemView.setOnClickListener {
            onItemClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<NewsItem>) {
        val oldItems = items.toList()
        items = newItems
        autoNotify(oldItems, items) { old, new -> old.id == new.id }
    }
}