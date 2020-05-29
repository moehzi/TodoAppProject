package com.tugas.todoapp.database

import android.util.Log
import androidx.lifecycle.LiveData

class TodoRepo(private val TodoDao:TodoDAO) {
    val allTodos = TodoDao.loadTodo()

    suspend fun insertTodo(todo: Todo){
        TodoDao.insert(todo)
    }
    suspend fun deleteTodo(todo: Todo) {
        TodoDao.delete(todo)
    }
    suspend fun updateTodo(todo: Todo){
        TodoDao.update(todo)
    }

    fun sortDateCreatedDesc(){
         TodoDao.sortCreatedDateDesc()
    }
   fun sortDateCreatedAsdAsc(){
       TodoDao.sortCreatedDateAsc()

    }
    fun sortDueDateDesc() {
        TodoDao.sortDueDateDesc()
    }

    fun sortDueDateAscend() {
        TodoDao.sortDueDateAscend()
    }
    fun search(title: String) : LiveData<List<Todo>>?{
        return TodoDao?.search(title)
    }


}