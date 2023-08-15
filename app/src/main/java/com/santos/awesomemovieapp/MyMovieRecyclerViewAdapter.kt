package com.santos.awesomemovieapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.santos.awesomemovieapp.placeholder.PlaceholderContent.PlaceholderItem
import com.santos.awesomemovieapp.databinding.FragmentItemBinding

interface MovieItemListener {
    fun onItemSelected(position: Int)
}

class MyMovieRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val listener: MovieItemListener
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bindItem(item)
        holder.view.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val view  = binding.root
        fun bindItem(item: PlaceholderItem) {
            binding.movieItem = item
            binding.executePendingBindings()
        }
    }
}