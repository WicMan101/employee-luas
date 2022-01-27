package com.bigwic.employeeluas.ui.landing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import com.bigwic.employeeluas.R
import com.bigwic.employeeluas.model.Tram

class ForecastAdapter(private val trams: List<Tram>, private val direction: String?) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stop_list_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tram = trams[position]
        holder.tvDirection.text = direction
        holder.tvDestination.text = tram.destination
        holder.tvDueMins.text =
            if (tram.dueMins.isDigitsOnly()) String.format("%s minute(s)", tram.dueMins) else tram.dueMins
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return trams.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDirection: TextView = itemView.findViewById(R.id.tvDirection)
        val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
        val tvDueMins: TextView = itemView.findViewById(R.id.tvDueMins)
    }
}