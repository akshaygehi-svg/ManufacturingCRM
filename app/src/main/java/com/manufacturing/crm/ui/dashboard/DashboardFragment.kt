package com.manufacturing.crm.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.manufacturing.crm.R
import com.manufacturing.crm.data.LeadStatus
import com.manufacturing.crm.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DashboardViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        viewModel.totalLeads.observe(viewLifecycleOwner) { count ->
            binding.tvTotalLeads.text = count.toString()
        }
        
        viewModel.newLeads.observe(viewLifecycleOwner) { count ->
            binding.tvNewLeads.text = count.toString()
        }
        
        viewModel.quotationSent.observe(viewLifecycleOwner) { count ->
            binding.tvQuotationSent.text = count.toString()
        }
        
        viewModel.wonLeads.observe(viewLifecycleOwner) { count ->
            binding.tvWonLeads.text = count.toString()
        }
        
        viewModel.totalValue.observe(viewLifecycleOwner) { value ->
            binding.tvTotalValue.text = "₹${String.format("%,.0f", value)}"
        }
        
        viewModel.pendingActivities.observe(viewLifecycleOwner) { count ->
            binding.tvPendingActivities.text = count.toString()
        }
    }
    
    private fun setupClickListeners() {
        binding.cardNewLeads.setOnClickListener {
            // Navigate to leads with NEW status filter
        }
        
        binding.cardQuotationSent.setOnClickListener {
            // Navigate to leads with QUOTATION_SENT status filter
        }
        
        binding.cardWonLeads.setOnClickListener {
            // Navigate to leads with WON status filter
        }
        
        binding.cardPendingActivities.setOnClickListener {
            // Navigate to activities
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 