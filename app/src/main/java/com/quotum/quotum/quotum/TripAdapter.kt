package com.quotum.quotum.quotum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quotum.quotum.quotum.models.GetTripLocationResponseModel

class TripAdapter(var data : GetTripLocationResponseModel) : RecyclerView.Adapter<TripAdapter.TripViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_trip, parent, false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.textViewFrom.text = data.getResult()?.get(position)?.getSource()
        holder.textViewDestination.text = data.getResult()?.get(position)?.getDestination()
        holder.textViewCost.text = data.getResult()?.get(position)?.getBudget().toString()
    }

    override fun getItemCount(): Int {
        return data.getResult()?.size!!
    }

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewFrom = itemView.findViewById(R.id.text_view_from) as TextView
        val textViewDestination = itemView.findViewById(R.id.text_view_destination) as TextView
        val textViewCost = itemView.findViewById(R.id.text_view_cost) as TextView

    }
}