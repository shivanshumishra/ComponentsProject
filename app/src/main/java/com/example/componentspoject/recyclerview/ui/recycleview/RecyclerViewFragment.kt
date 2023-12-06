package com.example.componentspoject.recyclerview.ui.recycleview

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.componentspoject.recyclerview.model.ListItem
import com.example.componentspoject.R
import com.example.componentspoject.databinding.FragmentRecyclerViewBinding
import com.example.componentspoject.recyclerview.RecyclerViewCalendarActivity
import com.example.componentspoject.recyclerview.ui.recycleview.adapter.ListAdapter
import com.example.componentspoject.recyclerview.utility.EditTextUtility


class RecyclerViewFragment : Fragment() {
    private lateinit var dataset : MutableList<String>
    private lateinit var customAdapter : ListAdapter
    private lateinit var viewVModel: RecycleViewVModel
    private lateinit var binding: FragmentRecyclerViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        viewVModel = ViewModelProvider(this,
            RecycleViewModelFactory(RecyclerViewCalendarActivity().itemsRepository)
        )[RecycleViewVModel::class.java]
        subscribe()

        customAdapter = ListAdapter(viewVModel.dataset.value ?: arrayListOf(),viewVModel::deleteItemFromList)
        val recyclerView: RecyclerView = binding.rvItems
        val addBtn : Button = binding.btnAdd
        val editText : EditText = binding.editTextItemValue
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = customAdapter
        addBtn.setOnClickListener {
            EditTextUtility.hideKeyboard(requireActivity())
            if(editText.text.isNullOrBlank()){
                Toast.makeText(requireContext(),"Please enter list item",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(binding.tvDate.text.contains("X")){
                Toast.makeText(requireContext(),"Please select item date",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val item = ListItem(
                value = editText.text.toString(),
                date = binding.tvDate.text.toString()
            )
            binding.tvDate.apply {
                text = "XX-XX-XXXX"
                setTextColor(resources.getColor(R.color.gray))
            }
            binding.editTextItemValue.setText("")
            viewVModel.addItemToList(item,this.context)
        }

        binding.ivCalendar.setOnClickListener {
            showDatePicker(it)
        }
        return view
    }

    private fun showDatePicker(view: View?) {
        val c = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = view?.context?.let {
            DatePickerDialog( // on below line we are passing context.
                it,
                { view, year, monthOfYear, dayOfMonth ->
                    binding.tvDate.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    binding.tvDate.setTextColor(resources.getColor(R.color.black,resources.newTheme()))
                },
                year, month, day
            )
        }
        datePickerDialog?.show()
    }

    private fun subscribe() {
        viewVModel.dataset.observe(viewLifecycleOwner) {
            customAdapter.setData(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}