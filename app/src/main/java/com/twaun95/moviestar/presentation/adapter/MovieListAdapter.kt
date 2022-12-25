package com.twaun95.moviestar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twaun95.moviestar.databinding.ItemMovieBinding
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.presentation.extensions.setOnSingleClickListener

class MovieListAdapter(
    var onItemClickListener: ((movie: MovieEntity, position: Int)->Unit)? = null
) : ListAdapter<MovieEntity, MovieListAdapter.MovieViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        return holder.bind(getItem(position)){ onItemClickListener?.invoke(getItem(position), position) }
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<MovieEntity>() {
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return (oldItem.title == newItem.title) && (oldItem.isBookMarked == newItem.isBookMarked)
            }
        }
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: MovieEntity,
            onClickListener: (()->Unit)? = null
        ) {
            binding.data = data
            binding.imageBookmark.setOnSingleClickListener {
                onClickListener?.invoke()
            }
        }
    }
}
