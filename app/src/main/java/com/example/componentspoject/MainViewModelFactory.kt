package com.example.componentspoject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.componentspoject.reposiotry.ItemRepository

class MainViewModelFactory(val itemRepository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(itemRepository) as T
    }
}