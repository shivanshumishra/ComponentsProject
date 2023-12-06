package com.example.componentspoject.recyclerview.db.addItemDB


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.componentspoject.recyclerview.model.ListItem

@Dao
interface AddItemDao  {
    @Query("SELECT * FROM listitem")
    suspend fun getListItem() : List<ListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListItem(listItem: ListItem)

    @Delete
    suspend fun deleteListItem(listItem: ListItem)
}