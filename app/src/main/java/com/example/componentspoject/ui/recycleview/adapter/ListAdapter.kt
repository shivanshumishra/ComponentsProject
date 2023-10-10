package com.example.componentspoject.ui.recycleview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.componentspoject.model.ListItem
import com.example.componentspoject.R

class ListAdapter(
    private val dataSet: ArrayList<ListItem>,
    val deleteItem: (Int) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var data : ArrayList<ListItem> = dataSet
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val deleteIcon : ImageView
        val dateTextView : TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.list_item_value)
            deleteIcon = view.findViewById(R.id.deleteIcon)
            dateTextView = view.findViewById(R.id.selectedDate)
        }
    }

    fun setData(data:ArrayList<ListItem>){
        this.data = data
        notifyItemRangeChanged(0,data.size)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].value
        viewHolder.dateTextView.text = dataSet[position].date
        viewHolder.deleteIcon.setOnClickListener {
            deleteItem(position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}