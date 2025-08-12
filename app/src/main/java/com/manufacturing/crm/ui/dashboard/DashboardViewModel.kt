package com.manufacturing.crm.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manufacturing.crm.data.AppDatabase
import com.manufacturing.crm.data.LeadStatus
import com.manufacturing.crm.repository.LeadRepository
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = LeadRepository(
        database.leadDao(),
        database.documentDao(),
        database.activityDao()
    )
    
    private val _totalLeads = MutableLiveData<Int>()
    val totalLeads: LiveData<Int> = _totalLeads
    
    private val _newLeads = MutableLiveData<Int>()
    val newLeads: LiveData<Int> = _newLeads
    
    private val _quotationSent = MutableLiveData<Int>()
    val quotationSent: LiveData<Int> = _quotationSent
    
    private val _wonLeads = MutableLiveData<Int>()
    val wonLeads: LiveData<Int> = _wonLeads
    
    private val _totalValue = MutableLiveData<Double>()
    val totalValue: LiveData<Double> = _totalValue
    
    private val _pendingActivities = MutableLiveData<Int>()
    val pendingActivities: LiveData<Int> = _pendingActivities
    
    init {
        loadDashboardData()
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            // Load lead counts by status
            _newLeads.value = repository.getLeadCountByStatus(LeadStatus.NEW)
            _quotationSent.value = repository.getLeadCountByStatus(LeadStatus.QUOTATION_SENT)
            _wonLeads.value = repository.getLeadCountByStatus(LeadStatus.WON)
            
            // Load total value
            val totalValue = repository.getTotalValueByStatus(LeadStatus.WON) ?: 0.0
            _totalValue.value = totalValue
            
            // Load pending activities count
            val pendingActivitiesList = repository.getPendingActivities().value
            _pendingActivities.value = pendingActivitiesList?.size ?: 0
        }
        
        // Observe all leads for total count
        repository.getAllLeads().observeForever { leads ->
            _totalLeads.value = leads.size
        }
    }
    
    fun refreshData() {
        loadDashboardData()
    }
} 