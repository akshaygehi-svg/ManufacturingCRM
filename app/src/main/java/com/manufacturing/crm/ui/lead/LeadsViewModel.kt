package com.manufacturing.crm.ui.lead

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manufacturing.crm.data.AppDatabase
import com.manufacturing.crm.data.Lead
import com.manufacturing.crm.data.LeadStatus
import com.manufacturing.crm.repository.LeadRepository

class LeadsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = LeadRepository(
        database.leadDao(),
        database.documentDao(),
        database.activityDao()
    )
    
    private val _leads = MutableLiveData<List<Lead>>()
    val leads: LiveData<List<Lead>> = _leads
    
    init {
        loadAllLeads()
    }
    
    fun loadAllLeads() {
        repository.getAllLeads().observeForever { leadsList ->
            _leads.value = leadsList
        }
    }
    
    fun filterLeadsByStatus(status: LeadStatus) {
        repository.getLeadsByStatus(status).observeForever { leadsList ->
            _leads.value = leadsList
        }
    }
    
    fun searchLeads(query: String) {
        if (query.isBlank()) {
            loadAllLeads()
        } else {
            repository.searchLeads(query).observeForever { leadsList ->
                _leads.value = leadsList
            }
        }
    }
} 