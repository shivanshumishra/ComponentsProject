package com.example.componentspoject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.componentspoject.databinding.ActivityMainBinding
import com.example.componentspoject.db.addItemDB.AddItemDatabase
import com.example.componentspoject.reposiotry.ItemRepository
import com.example.componentspoject.ui.calendar.CalendarFragment
import com.example.componentspoject.ui.recycleview.RecyclerViewFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewFragment : RecyclerViewFragment
    private lateinit var viewModel: MainViewModel
    private val database: AddItemDatabase by lazy {
        AddItemDatabase.getDatabase(this)
    }
    private val itemsRepository : ItemRepository by lazy {
        ItemRepository(database.addItemDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
            Toast.makeText(this,"Button Clicked",Toast.LENGTH_LONG).show()
        }
        setContentView(view)
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }
}