package com.example.componentspoject.reposiotry

import androidx.lifecycle.LiveData
import com.example.componentspoject.db.addItemDB.AddItemDao
import com.example.componentspoject.model.ListItem

class ItemRepository(private val addItemDao: AddItemDao) {

    suspend fun getAllItem() : List<ListItem>? {
        return addItemDao.getListItem()
    }

    suspend fun insertItem(listItem: ListItem){
        addItemDao.addListItem(listItem)
    }
}