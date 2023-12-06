package com.example.componentspoject.recyclerview.ui.recycleview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.componentspoject.recyclerview.reposiotry.ItemRepository

class RecycleViewModelFactory(val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecycleViewVModel(repository = repository) as T
    }
}