package com.example.uklaplikasiku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FrenAdapter (private val context: Context, private val items: ArrayList<Fren>):
    RecyclerView.Adapter<FrenAdapter.ViewHolder>(){
    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        private var txtFriendName: TextView = itemView.findViewById(R.id.txtFriendName)
        private var txtFriendEmail: TextView = itemView.findViewById(R.id.txtFriendEmail)
        private var txtFriendUsername: TextView = itemView.findViewById(R.id.txtFriendUsername)

        fun bindItem(item: Fren) {
            txtFriendName.text = item.nama
            txtFriendEmail.text = item.email
            txtFriendUsername.text = item.username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.fren_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}