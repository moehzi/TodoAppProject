package com.tugas.todoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Todo(
    @PrimaryKey (autoGenerate = true) var id : Int,
    var title:String,
    var task:String,
    var date:String
)
