package com.tugas.todoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tugas.todoapp.database.Todo
import com.tugas.todoapp.database.TodoDAO
import com.tugas.todoapp.database.TodoDatabase
import com.tugas.todoapp.database.TodoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) :AndroidViewModel(application) {
    //add repo
    private val repository: TodoRepo
    private val todoDao: TodoDAO
    private var _todos: LiveData<List<Todo>>
    val todos: LiveData<List<Todo>>
        get() = _todos

    //coroutine
    private var vmJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + vmJob)

    init {
        todoDao = TodoDatabase.getInstance(application).todoDao()
        repository = TodoRepo(todoDao)
        _todos = repository.allTodos
    }

    fun createDoes(text: String,text1:String,text2:String) {
        uiScope.launch {
            repository.insertTodo(Todo(0,text,text1,text2))
        }
    }


        fun removeTodo(todo: Todo) {
            uiScope.launch {
                repository.deleteTodo(todo)
            }
        }

        fun updateTodo(todo:Todo) {
            uiScope.launch {
                repository.updateTodo(todo)
            }
        }
        override fun onCleared() {
            super.onCleared()
            vmJob.cancel()
        }

    }
