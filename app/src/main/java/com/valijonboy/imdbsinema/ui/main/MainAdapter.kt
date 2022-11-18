package com.valijonboy.imdbsinema.ui.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valijonboy.imdbsinema.R
import com.valijonboy.imdbsinema.app.startActivity
import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.databinding.ListItemBinding
import com.valijonboy.imdbsinema.di.Injector
import com.valijonboy.imdbsinema.ui.details.DetailsActivity

class MainAdapter(var list: List<Movie>?) : RecyclerView.Adapter<MainAdapter.VH>(){

    private var isFavorite: Int = 0

    inner class VH(private val itemBinding: ListItemBinding):
    RecyclerView.ViewHolder(itemBinding.root){

        fun onBind(position: Int, context: Context) {
            val movie = list!![position]
            movie.poster.let {
                Glide.with(context)
                    .load(it)
                    .placeholder(R.drawable.ic_image)
                    .into(itemBinding.posterIv)
            }

            Log.d("adapter", "onBind: title is: ${movie.title}")

             itemBinding.titleMovie.text = movie.title
             itemBinding.movieYear.text =  movie.year
             itemBinding.typeMovie.text =   movie.type
             itemBinding.countVotes.text = movie.voted


            itemBinding.imageLike.setOnClickListener {
                itemBinding.imageLike.setImageResource(R.drawable.liked)
            }

            isFavorite = if (itemBinding.imageLike.resources.equals(R.drawable.liked)) {
                1
            }else {
                0
            }

            itemBinding.listItem.setOnClickListener{
                movie.imdbID?.let { it1 ->
                    context.startActivity<DetailsActivity>(
                        "isFavorite", isFavorite,
                        "imdbID", it1
                    )
                }

            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       return VH(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(position, holder.itemView.context)
    }

    override fun getItemCount(): Int = list!!.count()


}