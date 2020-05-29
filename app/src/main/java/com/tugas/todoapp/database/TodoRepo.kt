package com.tugas.todoapp.database

import android.util.Log
import androidx.lifecycle.LiveData

class TodoRepo(private val TodoDao:TodoDAO) {
    val allTodos = TodoDao.loadTodo()

    suspend fun insertTodo(todo: Todo){
        Log.d("Debug","Fungsi create repo")
        TodoDao.insert(todo)
    }
    suspend fun deleteTodo(todo: Todo) {
        Log.d("Debug","Fungsi remove repo")
        TodoDao.delete(todo)
    }
    suspend fun updateTodo(todo: Todo){
        TodoDao.update(todo)
    }

    fun sortDateCreatedDesc(){

       Log.d("Debug", "fungsi sort date created repo")
         TodoDao.sortCreatedDateDesc()
    }
   fun sortDateCreatedAsdAsc(){
       Log.d("Debug", "fungsi sort date created repo")
       TodoDao.sortCreatedDateAsc()

    }
    fun sortDueDateDesc() {
        TodoDao.sortDueDateDesc()
    }

    fun sortDueDateAscend() {
        TodoDao.sortDueDateAscend()
    }


}