package com.tugas.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel :ViewModel(){
    private val _todos = MutableLiveData<ArrayList<Todo>>()
    val todos : LiveData<ArrayList<Todo>>
    get() = _todos

    init {
        _todos.value = arrayListOf(
            Todo(1,"Mandi","Nanti mandi jam 10"),
            Todo(2,"Berak","Nanti berak sama dedek")
        )
    }
    fun createDoes(text: String,text1:String){
        var newId = _todos.value!!.size +1
        _todos.value!!.add(Todo(newId,text,text1))
        _todos.setValue ( _todos.value)
    }
    fun removeTodo(pos: Int){
        _todos.value!!.removeAt(pos)
        _todos.setValue ( _todos.value)
    }
    fun updateTodo(pos: Int,text:String,text1: String){
        _todos.value!![pos].title = text
        _todos.value!![pos].task = text1
        _todos.setValue ( _todos.value)
    }
}