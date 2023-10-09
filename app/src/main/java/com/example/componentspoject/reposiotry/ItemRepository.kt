package com.example.componentspoject.reposiotry

import com.example.componentspoject.db.addItemDB.AddItemDao
import com.example.componentspoject.model.ListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemRepository(val addItemDao: AddItemDao) {

    suspend fun getAllItem() : List<ListItem> {
        return addItemDao.getListItem()
    }

    suspend fun insertItem(listItem: ListItem){
        addItemDao.addListItem(listItem)
    }
}