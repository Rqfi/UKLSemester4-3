package com.example.uklaplikasiku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridBookAdapter(private val gridBook: ArrayList<Book>):
    RecyclerView.Adapter<GridBookAdapter.GridViewHolder>(){
    class GridViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid_book, viewGroup, false)
        return GridViewHolder(view)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(gridBook[position].photo)
            .apply(RequestOptions().override(350,550))
            .into(holder.imgPhoto)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(gridBook[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Book)
    }

    override fun getItemCount(): Int {
        return gridBook.size
    }

}