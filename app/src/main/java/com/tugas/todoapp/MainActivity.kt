package com.tugas.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugas.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val todos = mutableListOf<String>("Bangun","Makan","Mandi",
                                                        "Berak","Kencing","Sikat gigi"
                                                        ,"Mandi","Belajar","Sekolah")
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = TodoAdapter(todos)

        binding.myRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}
