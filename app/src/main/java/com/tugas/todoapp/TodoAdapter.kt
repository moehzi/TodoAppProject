package com.tugas.todoapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugas.todoapp.database.Todo
import com.tugas.todoapp.databinding.ListItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TodoAdapter(private val viewModel: TodoViewModel) :
    ListAdapter<Todo, TodoAdapter.MyViewHolder>(TodoDiffCallBack()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    @SuppressLint("InflateParams")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.todoText.text = getItem(position).task
        holder.titleText.text = getItem(position).title
        holder.dateCrt.text = getItem(position).date
        //Menghapus
        holder.btnDel.setOnClickListener {
            viewModel.removeTodo(getItem(holder.adapterPosition))
        }
        //Edit
        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_layout,null)

        //Menganmbil data
            val prevTitle =getItem(position).title
            val prevTask = getItem(position).task
            val prevDate = getItem(position).date
            val editTitle = view.findViewById<TextView>(R.id.editTitle)
            editTitle.text = prevTitle
            val editTask = view.findViewById<TextView>(R.id.editTask)
            editTask.text = prevTask
            val updateBtn = view.findViewById<TextView>(R.id.update_btn)
            val cancelBtn = view.findViewById<TextView>(R.id.cancel1_btn)
            val dateUpdate = view.findViewById<TextView>(R.id.date_update)
            dateUpdate.text = prevDate
            val calendar = Calendar.getInstance()
            val simpleFormat = SimpleDateFormat("hh:mm:ss a")
            val time = simpleFormat.format(calendar.time)
            val currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.time)
            dateUpdate.setText("Updated : $currentDate $time")
            //Membuat dial9og
            val alertDialog = AlertDialog.Builder(context).setView(view).show()
                updateBtn.setOnClickListener {
                    val editedTitle = editTitle.text.toString()
                    val editedTask = editTask.text.toString()
                    val editedDate = dateUpdate.text.toString()
                    getItem(holder.adapterPosition).title = editedTitle
                    getItem(holder.adapterPosition).task = editedTask
                    getItem(holder.adapterPosition).date = editedDate
                    viewModel.updateTodo(getItem(holder.adapterPosition))
                    holder.titleText.text = editedTitle
                    holder.todoText.text = editedTask
                    holder.dateCrt.text = editedDate
                    alertDialog.dismiss()
                }
                cancelBtn.setOnClickListener{
                    alertDialog.dismiss()
                }
            alertDialog.create()
        }
    }

   // override fun getItemCount() = viewModel.todos.value!!.size
    class MyViewHolder(private val binding:ListItemBinding ) : RecyclerView.ViewHolder(binding.root) {
        val titleText = binding.titleText
        val todoText = binding.taskText
        val btnDel = binding.btnDel
        val btnEdit = binding.btnEdit
        val dateCrt = binding.createDate
    }

    class TodoDiffCallBack:DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.equals(newItem)
        }

    }
}
