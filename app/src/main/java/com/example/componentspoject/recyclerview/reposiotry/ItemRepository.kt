package com.example.componentspoject.recyclerview.reposiotry

import com.example.componentspoject.recyclerview.db.addItemDB.AddItemDao
import com.example.componentspoject.recyclerview.model.ListItem

class ItemRepository(private val addItemDao: AddItemDao) {

    suspend fun getAllItem() : List<ListItem>? {
        return addItemDao.getListItem()
    }

    suspend fun insertItem(listItem: ListItem){
        addItemDao.addListItem(listItem)
    }
}