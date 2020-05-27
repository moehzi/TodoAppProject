package com.tugas.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo")
    fun loadTodo() : LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}