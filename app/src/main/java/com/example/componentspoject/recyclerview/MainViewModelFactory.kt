package com.example.componentspoject.recyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.componentspoject.recyclerview.reposiotry.ItemRepository

class MainViewModelFactory(val itemRepository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(itemRepository) as T
    }
}