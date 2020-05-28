package com.tugas.todoapp

import android.annotation.SuppressLint
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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: TodoAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: TodoViewModel

    @SuppressLint("SimpleDateFormat")
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
            val dateCreate = view.findViewById<TextView>(R.id.date_new)
            val calendar = Calendar.getInstance()
            val simpleFormat = SimpleDateFormat("hh:mm:ss a")
            val time = simpleFormat.format(calendar.time)
            val currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
            dateCreate.setText("Created : $currentDate $time")

            //Dialog
            var alertDialog = AlertDialog.Builder(this).setView(view).show()
            addBtn.setOnClickListener {
                viewModel.createDoes(newTitle.text.toString(),newTask.text.toString(),dateCreate.text.toString())
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
