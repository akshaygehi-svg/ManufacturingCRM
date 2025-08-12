package com.manufacturing.crm.ui.lead

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manufacturing.crm.data.Lead
import com.manufacturing.crm.data.LeadStatus
import com.manufacturing.crm.data.Priority
import com.manufacturing.crm.databinding.ItemLeadBinding
import java.text.SimpleDateFormat
import java.util.*

class LeadsAdapter(private val onLeadClick: (Lead) -> Unit) : 
    ListAdapter<Lead, LeadsAdapter.LeadViewHolder>(LeadDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadViewHolder {
        val binding = ItemLeadBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LeadViewHolder(binding, onLeadClick)
    }
    
    override fun onBindViewHolder(holder: LeadViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class LeadViewHolder(
        private val binding: ItemLeadBinding,
        private val onLeadClick: (Lead) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        fun bind(lead: Lead) {
            binding.apply {
                tvCompanyName.text = lead.companyName
                tvContactPerson.text = lead.contactPerson
                tvProjectDescription.text = lead.projectDescription
                tvEstimatedValue.text = "₹${String.format("%,.0f", lead.estimatedValue)}"
                tvCreatedDate.text = dateFormat.format(lead.createdAt)
                
                // Set status chip
                chipStatus.text = getStatusText(lead.status)
                chipStatus.setChipBackgroundColorResource(getStatusColor(lead.status))
                
                // Set priority indicator
                val priorityColor = when (lead.priority) {
                    Priority.LOW -> android.R.color.holo_green_light
                    Priority.MEDIUM -> android.R.color.holo_orange_light
                    Priority.HIGH -> android.R.color.holo_red_light
                    Priority.URGENT -> android.R.color.holo_red_dark
                }
                viewPriorityIndicator.setBackgroundResource(priorityColor)
                
                root.setOnClickListener { onLeadClick(lead) }
            }
        }
        
        private fun getStatusText(status: LeadStatus): String {
            return when (status) {
                LeadStatus.NEW -> "New"
                LeadStatus.CONTACTED -> "Contacted"
                LeadStatus.QUOTATION_SENT -> "Quotation Sent"
                LeadStatus.NEGOTIATION -> "Negotiation"
                LeadStatus.WON -> "Won"
                LeadStatus.LOST -> "Lost"
                LeadStatus.ON_HOLD -> "On Hold"
            }
        }
        
        private fun getStatusColor(status: LeadStatus): Int {
            return when (status) {
                LeadStatus.NEW -> android.R.color.holo_blue_light
                LeadStatus.CONTACTED -> android.R.color.holo_orange_light
                LeadStatus.QUOTATION_SENT -> android.R.color.holo_purple
                LeadStatus.NEGOTIATION -> android.R.color.holo_orange_dark
                LeadStatus.WON -> android.R.color.holo_green_dark
                LeadStatus.LOST -> android.R.color.holo_red_dark
                LeadStatus.ON_HOLD -> android.R.color.darker_gray
            }
        }
    }
    
    class LeadDiffCallback : DiffUtil.ItemCallback<Lead>() {
        override fun areItemsTheSame(oldItem: Lead, newItem: Lead): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Lead, newItem: Lead): Boolean {
            return oldItem == newItem
        }
    }
} 