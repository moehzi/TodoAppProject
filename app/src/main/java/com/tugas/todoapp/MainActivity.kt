package com.tugas.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugas.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TodoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = TodoAdapter(viewModel)

        recyclerView = binding.myRecyclerView
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }
        val btnNew = binding.btnNew
        // Add data
        btnNew.setOnClickListener{
            val inflater = LayoutInflater.from(this)
            val view = inflater.inflate(R.layout.new_layout,null)
            val newTitle =  view.findViewById<TextView>(R.id.editTitle)
            val newTask =  view.findViewById<TextView>(R.id.editTask)
            val addBtn = view.findViewById<TextView>(R.id.update_btn)
            val cancelBtn = view.findViewById<TextView>(R.id.cancel1_btn)

            //Dialog
            var alertDialog = AlertDialog.Builder(this).setView(view).show()
            addBtn.setOnClickListener {

                viewModel.createDoes(newTitle.text.toString(),newTask.text.toString())
                alertDialog.dismiss()

            }
            cancelBtn.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.create()
        }
        viewModel.todos.observe(this, Observer { list->
            viewAdapter.submitList(list.toMutableList())
        })
    }

}
