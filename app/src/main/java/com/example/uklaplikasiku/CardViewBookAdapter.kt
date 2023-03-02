package com.example.uklaplikasiku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewBookAdapter(private val listBook: ArrayList<Book>):
    RecyclerView.Adapter<CardViewBookAdapter.CardViewViewHolder>() {
    class CardViewViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvType: TextView = itemView.findViewById(R.id.tv_item_type)
        var btnFavorite: ImageButton = itemView.findViewById(R.id.btn_set_favorite)
        var btnShare: ImageButton = itemView.findViewById(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_book, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val (name, type, photo) = listBook[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvType.text = type

        holder.btnFavorite.setOnClickListener { Toast.makeText(holder.itemView.context, "Favorite " + listBook[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }

        holder.btnShare.setOnClickListener { Toast.makeText(holder.itemView.context, "Share " + listBook[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }

        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context, "Kamu memilih " + listBook[holder.adapterPosition].name, Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return listBook.size
    }
}