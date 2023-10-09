package com.example.componentspoject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listitem")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val  id: Int = 0,
    @ColumnInfo(name = "value")
    var value: String,
    @ColumnInfo(name = "date")
    var date: String
)
