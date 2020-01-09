package com.sembozdemir.nytimesdemo.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: NewsItem) {
        with(itemView) {
            newsItemImageView.load(item.bannerUrl)
            newsItemTextViewTitle.text = item.title
            newsItemTextViewAbstract.text = item.abstract
            newsItemTextViewPublisher.text = item.publisher
            newsItemTextViewDate.text = item.date
        }
    }
}
