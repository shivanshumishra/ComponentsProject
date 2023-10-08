package com.example.componentspoject.ui.recycleview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.componentspoject.model.ListItem

class RecycleViewVModel : ViewModel(){
    private val _dataSet : MutableLiveData<ArrayList<ListItem>> = MutableLiveData<ArrayList<ListItem>>(
        arrayListOf())
    val dataset = _dataSet


    fun addItemToList(item : ListItem) {
        val currentList = _dataSet.value ?: ArrayList()
        currentList.add(item)
        _dataSet.postValue(currentList)
    }

    fun deleteItemFromList(index : Int) {
        val currentList = _dataSet.value ?: ArrayList()
        if (currentList.isNotEmpty() && index >= 0 && index < currentList.size) {
            currentList.removeAt(index)
            _dataSet.postValue(currentList)
        }
    }
}