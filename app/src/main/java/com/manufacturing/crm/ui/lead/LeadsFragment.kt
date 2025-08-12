package com.manufacturing.crm.ui.lead

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.manufacturing.crm.R
import com.manufacturing.crm.data.LeadStatus
import com.manufacturing.crm.databinding.FragmentLeadsBinding

class LeadsFragment : Fragment() {
    
    private var _binding: FragmentLeadsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LeadsViewModel
    private lateinit var adapter: LeadsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeadsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[LeadsViewModel::class.java]
        setupRecyclerView()
        setupSearchView()
        setupChipGroup()
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupRecyclerView() {
        adapter = LeadsAdapter { lead ->
            // Navigate to lead detail
            val intent = Intent(requireContext(), LeadDetailActivity::class.java)
            intent.putExtra("lead_id", lead.id)
            startActivity(intent)
        }
        
        binding.recyclerViewLeads.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@LeadsFragment.adapter
        }
    }
    
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchLeads(it) }
                return true
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchLeads(it) }
                return true
            }
        })
    }
    
    private fun setupChipGroup() {
        binding.chipGroupStatus.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isEmpty()) {
                viewModel.loadAllLeads()
            } else {
                val chip = group.findViewById<Chip>(checkedIds[0])
                val status = when (chip.text) {
                    "New" -> LeadStatus.NEW
                    "Contacted" -> LeadStatus.CONTACTED
                    "Quotation Sent" -> LeadStatus.QUOTATION_SENT
                    "Negotiation" -> LeadStatus.NEGOTIATION
                    "Won" -> LeadStatus.WON
                    "Lost" -> LeadStatus.LOST
                    else -> LeadStatus.NEW
                }
                viewModel.filterLeadsByStatus(status)
            }
        }
    }
    
    private fun setupObservers() {
        viewModel.leads.observe(viewLifecycleOwner) { leads ->
            adapter.submitList(leads)
            binding.tvEmptyState.visibility = if (leads.isEmpty()) View.VISIBLE else View.GONE
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddLead.setOnClickListener {
            val intent = Intent(requireContext(), AddLeadActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 