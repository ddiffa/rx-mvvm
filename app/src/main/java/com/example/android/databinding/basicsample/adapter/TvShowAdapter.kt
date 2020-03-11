package com.example.android.databinding.basicsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.databinding.ItemTvshowBinding
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.ResultsItem
import com.example.android.databinding.basicsample.handler.EventHandler

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val list = ArrayList<ResultsItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTvshowBinding>(inflater, R.layout.item_tvshow, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position]?.let { holder.bind(it) }
    }
    fun setTvShows(list: List<ResultsItem?>) {
        if (list == null) return
        this.list.clear()
        this.list.addAll(list)
    }
    class ViewHolder(var binding: ItemTvshowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: ResultsItem) {
            binding.tvShows = tvShow
            binding.image = tvShow.posterPath
            binding.handler = EventHandler(binding.root.context)
        }

    }

}