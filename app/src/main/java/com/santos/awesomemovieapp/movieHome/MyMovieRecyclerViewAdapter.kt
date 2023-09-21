package com.santos.awesomemovieapp.movieHome

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.databinding.FragmentItemBinding

interface MovieItemListener {
    fun onItemSelected(position: Int)
}

interface OnScrollListener {
    fun onScrolled()
}

class MyMovieRecyclerViewAdapter(
    private val listener: MovieItemListener
) : RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Movie> = ArrayList()
    private var scrollListener: OnScrollListener? = null

    fun updateData(movieList: List<Movie>) {
        values = movieList
        notifyDataSetChanged()
    }

    fun setOnScrollListener(listener: OnScrollListener) {
        this.scrollListener = listener
    }


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
        fun bindItem(item: Movie) {
            binding.movieItem = item
            binding.executePendingBindings()
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                // Obtém a posição do primeiro item visível
                val primeiraPosicaoVisivel = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                // Obtém a posição do último item visível
                val ultimaPosicaoVisivel = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                // Imprime a posição dos itens visíveis
                Log.d("Primeira posição visível:", "$primeiraPosicaoVisivel")
                Log.d("Última posição visível:", "$ultimaPosicaoVisivel")

                // Chama o listener de rolagem, se definido
                scrollListener?.onScrolled()
            }
        })
    }
}