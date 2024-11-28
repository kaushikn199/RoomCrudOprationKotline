package com.example.roomcrudoprationkotline.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcrudoprationkotline.Note
import com.example.roomcrudoprationkotline.R

class CustomAdapter(
    private val dataSet: List<Note>,
    private val onItemClick: (flag: Int, pos: Int) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val textView: TextView = view.findViewById(R.id.textView)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
        val ivEdit: ImageView = view.findViewById(R.id.ivEdit)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].title


        viewHolder.ivDelete.setOnClickListener { onItemClick(1, position) }
        viewHolder.ivEdit.setOnClickListener { onItemClick(2, position) }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}