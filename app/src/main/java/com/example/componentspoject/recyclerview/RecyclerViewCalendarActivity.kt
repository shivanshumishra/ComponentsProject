package com.example.componentspoject.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.componentspoject.R
import com.example.componentspoject.databinding.ActivityRecyclerViewCalendarBinding
import com.example.componentspoject.recyclerview.db.addItemDB.AddItemDatabase
import com.example.componentspoject.recyclerview.reposiotry.ItemRepository
import com.example.componentspoject.recyclerview.ui.calendar.CalendarFragment
import com.example.componentspoject.recyclerview.ui.recycleview.RecyclerViewFragment

class RecyclerViewCalendarActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewCalendarBinding
    private lateinit var recyclerViewFragment : RecyclerViewFragment
    private lateinit var viewModel: MainViewModel
    private val database: AddItemDatabase by lazy {
        AddItemDatabase.getDatabase(this)
    }
    val itemsRepository : ItemRepository by lazy {
        ItemRepository(database.addItemDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewCalendarBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this, MainViewModelFactory(itemsRepository)).get(MainViewModel::class.java)
        recyclerViewFragment = RecyclerViewFragment()
        switchFragment(recyclerViewFragment)
        val calendarFragment = CalendarFragment()
        binding.fragment1.setOnClickListener {
            switchFragment(recyclerViewFragment)
        }
        binding.calendarFragment.setOnClickListener {
            switchFragment(calendarFragment)
        }
        binding.fab.setOnClickListener {
            Toast.makeText(this,"Button Clicked", Toast.LENGTH_LONG).show()
        }
        setContentView(view)
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }
}