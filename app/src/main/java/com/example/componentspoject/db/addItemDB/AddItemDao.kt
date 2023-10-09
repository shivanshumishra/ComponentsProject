package com.example.componentspoject.db.addItemDB


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.componentspoject.model.ListItem

@Dao
interface AddItemDao  {
    @Query("select * from listitem")
    suspend fun getListItem() : List<ListItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListItem(listItem: ListItem)

    @Delete
    suspend fun deleteListItem(listItem: ListItem)
}