package com.mac.flights.ui.flightList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mac.flights.Config
import com.mac.flights.R
import com.mac.flights.databinding.ListItemFlightsBinding
import com.mac.flights.model.FlightsData
import java.util.*

class FlightListAdapter(private var onFlightClickListener: ((FlightsData, String) -> Unit)? = null) :
    ListAdapter<FlightsData, FlightListAdapter.FlightsViewHolder>(FlightListComparator){

    private var currency: String = "EUR"

    fun updateCurrency(currency: String){
        this.currency = currency
    }

    fun setOnFlightClickListener(listener: (FlightsData, String) -> Unit) {
        onFlightClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        return FlightsViewHolder(parent.context,
            ListItemFlightsBinding.inflate(LayoutInflater.from(parent.context),parent,false),
            currency, onFlightClickListener)
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    class FlightsViewHolder(private val context: Context, private val itemBinding:ListItemFlightsBinding,
                            private val currency: String, private var listener:((FlightsData, String) -> Unit)? = null ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindViews(flight: FlightsData) = with(itemBinding) {
            Glide.with(context).load(Config.IMAGE_URL +flight.mapIdto+".jpg")
                .apply(RequestOptions().centerInside()).into(ivDestination)
            tvCountryName.text = flight.countryTo.name
            tvCityName.text = flight.cityTo
            tvFlightPrice.text = String.format(Locale.getDefault(),"%s%s", currency, flight.price)

            itemView.setOnClickListener{
                listener?.invoke(flight, currency)
            }
        }
    }

    object FlightListComparator: DiffUtil.ItemCallback<FlightsData>(){
        override fun areItemsTheSame(oldItem: FlightsData, newItem: FlightsData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlightsData, newItem: FlightsData): Boolean {
            return oldItem == newItem
        }

    }


}