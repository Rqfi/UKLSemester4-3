package com.example.uklaplikasiku

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers.Main

class FrenFragment : Fragment(){
    private var fabAddFren: FloatingActionButton? = null
    private var listFren: RecyclerView? = null
    private var listTeman: List<Fren>? = null
    private var db: AppDatabase? = null
    private var frenDao: FrenDao? = null

    companion object{
        fun newInstance():FrenFragment{
            return FrenFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fren_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        initLocalDB()
        initView()
    }

    private fun initLocalDB(){
        db = AppDatabase.getAppDataBase(requireActivity())
        frenDao = db?.frenDao()
    }

    private fun initView(){
        fabAddFren = activity?.findViewById(R.id.fabAddFren)
        listFren = activity?.findViewById(R.id.listFren)
        fabAddFren?.setOnClickListener{
            (activity as FrenActivity).tampilMyFriendAddFragment()
        }
        ambilDataTeman()
    }

    private fun ambilDataTeman(){
        listTeman = ArrayList()
        frenDao?.ambilSemuaTeman()?.observe(requireActivity()) { r -> listTeman = r
                when {
                    listTeman?.size == 0 -> tampilToast("Belum ada data Teman")
                    else -> {
                        tampilTeman()
                    }
                }
        }
    }

    private fun tampilToast(message: String){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        fabAddFren = null
        listFren = null
    }

    private fun simulisiDataTeman(){
        listTeman = ArrayList()
    }

    private fun tampilTeman(){
        listFren?.layoutManager = LinearLayoutManager(activity)
        listFren?.adapter = FrenAdapter(requireActivity(),
        listTeman!! as ArrayList<Fren>)
    }

}