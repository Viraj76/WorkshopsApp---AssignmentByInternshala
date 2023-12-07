package com.example.workshops.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.workshops.R
import com.example.workshops.databinding.ItemViewWorkshopsBinding
import com.example.workshops.roomDb.Workshops

class AdapterWorkshop(
    val context : Context,
    val onApplyButtonClicked: (Workshops) -> Unit
) : RecyclerView.Adapter<AdapterWorkshop.WorkShopViewHolder>() {
    class WorkShopViewHolder(val binding : ItemViewWorkshopsBinding)  : ViewHolder(binding.root)

    val diffUtil = object : ItemCallback<Workshops>(){
        override fun areItemsTheSame(oldItem: Workshops, newItem: Workshops): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Workshops, newItem: Workshops): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkShopViewHolder {
        return WorkShopViewHolder(ItemViewWorkshopsBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: WorkShopViewHolder, position: Int) {
        val workshop = differ.currentList[position]
        holder.binding.apply {
            tvWorkShopTitle.text = workshop.workshopName
            tvWorkshopDate.text = workshop.workShopDate
            if(workshop.applied == 1){
                btnApply.apply {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
                    text = "Applied"
                }
            }
            btnApply.setOnClickListener {
                onApplyButtonClicked(workshop)
            }
        }

    }
}