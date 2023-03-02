package com.example.uklaplikasiku

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LibraryActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvBooks : RecyclerView
    private var list:ArrayList<Book> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        rvBooks = findViewById(R.id.rv_book)
        rvBooks.setHasFixedSize(true)

        list.addAll(BooksData.listData)
        showRecyclerList()

        val btnListView: ImageButton =
            findViewById(R.id.list_button)
        btnListView.setOnClickListener(this)

        val btnGridView: ImageButton =
            findViewById(R.id.grid_button)
        btnGridView.setOnClickListener(this)

        val btnCardView: ImageButton =
            findViewById(R.id.cardview_button)
        btnCardView.setOnClickListener(this)

        val btnBackToHome: ImageButton =
            findViewById(R.id.back_button)
        btnBackToHome.setOnClickListener(this)
    }

    private fun showRecyclerList(){
        rvBooks.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(list)
        rvBooks.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Book) {
                showSelectedBook(data)
            }
        })
    }

    private fun showRecyclerGrid(){
        rvBooks.layoutManager = GridLayoutManager(this, 2)
        val gridBookAdapter = GridBookAdapter(list)
        rvBooks.adapter = gridBookAdapter

        gridBookAdapter.setOnItemClickCallback(object: GridBookAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Book) {
                showSelectedBook(data)
            }
        })
    }

    private fun showRecyclerCardView(){
        rvBooks.layoutManager = LinearLayoutManager(this)
        val cardViewBookAdapter = CardViewBookAdapter(list)
        rvBooks.adapter = cardViewBookAdapter
    }

    private fun showSelectedBook(book : Book){
        Toast.makeText(this, "Kamu memilih " + book.name, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View){
        when (v.id){
            R.id.list_button -> {
                showRecyclerList()
            }
            R.id.grid_button -> {
                showRecyclerGrid()
            }
            R.id.cardview_button -> {
                showRecyclerCardView()
            }
            R.id.back_button -> {
                val moveToHome = Intent(this@LibraryActivity, HomeActivity::class.java)
                startActivity(moveToHome)
            }
        }
    }
}
