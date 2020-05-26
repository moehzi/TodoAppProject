package com.tugas.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tugas.todoapp.databinding.ListItemBinding

class TodoAdapter(private val myDataset: MutableList<String>) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    class MyViewHolder (val  textView: View) : RecyclerView.ViewHolder(textView){
        val todoText = itemView.findViewById<TextView>(R.id.task_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TodoAdapter.MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.todoText.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size

}