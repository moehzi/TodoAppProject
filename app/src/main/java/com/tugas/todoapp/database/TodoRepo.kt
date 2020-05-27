package com.tugas.todoapp.database

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

}