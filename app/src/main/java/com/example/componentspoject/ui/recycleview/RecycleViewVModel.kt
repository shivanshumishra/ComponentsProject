package com.example.componentspoject.ui.recycleview

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.componentspoject.model.ListItem
import com.example.componentspoject.reposiotry.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecycleViewVModel(val repository: ItemRepository) : ViewModel(){
    private val _dataSet : MutableLiveData<ArrayList<ListItem>> = MutableLiveData<ArrayList<ListItem>>(
        arrayListOf())
    val dataset = _dataSet

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllItem()?.let {
                _dataSet.postValue(ArrayList(it))
            }
        }
    }


    fun addItemToList(item : ListItem, context: Context?) {
        val currentList = _dataSet.value ?: ArrayList()
        currentList.add(item)
        viewModelScope.launch {
            repository.insertItem(item)
        }
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