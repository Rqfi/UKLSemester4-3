package com.example.uklaplikasiku

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListBookAdapter(private val listBook: ArrayList<Book>):
    RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvType: TextView = itemView.findViewById(R.id.tv_item_type)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_book, viewGroup, false)
        return ListViewHolder(view)

    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, type, photo) = listBook[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(70, 110))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvType.text = type
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listBook[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Book)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }
}

