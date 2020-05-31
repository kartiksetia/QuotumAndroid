package com.quotum.quotum.quotum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

 class TripAdapter : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_trip, parent, false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {}
    override fun getItemCount(): Int {
        return 8
    }

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}