package com.manufacturing.crm.ui.document

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.manufacturing.crm.data.DocumentType
import com.manufacturing.crm.databinding.FragmentDocumentsBinding

class DocumentsFragment : Fragment() {
    
    private var _binding: FragmentDocumentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DocumentsViewModel
    private lateinit var adapter: DocumentsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocumentsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[DocumentsViewModel::class.java]
        setupRecyclerView()
        setupSearchView()
        setupChipGroup()
        setupObservers()
    }
    
    private fun setupRecyclerView() {
        adapter = DocumentsAdapter { document ->
            // Open document viewer
            // Intent to DocumentViewerActivity
        }
        
        binding.recyclerViewDocuments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DocumentsFragment.adapter
        }
    }
    
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchDocuments(it) }
                return true
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchDocuments(it) }
                return true
            }
        })
    }
    
    private fun setupChipGroup() {
        binding.chipGroupType.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isEmpty()) {
                viewModel.loadAllDocuments()
            } else {
                val chip = group.findViewById<Chip>(checkedIds[0])
                val documentType = when (chip.text) {
                    "Quotation" -> DocumentType.QUOTATION
                    "Drawing Draft" -> DocumentType.DRAWING_DRAFT
                    "Drawing Revision" -> DocumentType.DRAWING_REVISION
                    "Final Quotation" -> DocumentType.FINAL_QUOTATION
                    "Technical Spec" -> DocumentType.TECHNICAL_SPECIFICATION
                    "Contract" -> DocumentType.CONTRACT
                    else -> DocumentType.OTHER
                }
                viewModel.filterDocumentsByType(documentType)
            }
        }
    }
    
    private fun setupObservers() {
        viewModel.documents.observe(viewLifecycleOwner) { documents ->
            adapter.submitList(documents)
            binding.tvEmptyState.visibility = if (documents.isEmpty()) View.VISIBLE else View.GONE
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 