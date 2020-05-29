package com.tugas.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo")
     fun loadTodo() : LiveData<List<Todo>>

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT*FROM todo ORDER BY date DESC")
    fun sortCreatedDateDesc(): LiveData<List<Todo>>

    @Query("SELECT*FROM todo ORDER BY date ASC")
    fun sortCreatedDateAsc() : LiveData<List<Todo>>
    @Query("SELECT * FROM todo ORDER BY deadlineDate DESC, deadlineTime DESC")
    fun sortDueDateDesc() : LiveData<List<Todo>>

    @Query("SELECT * FROM todo ORDER BY deadlineDate ASC, deadlineTime ASC")
    fun sortDueDateAscend() : LiveData<List<Todo>>
}