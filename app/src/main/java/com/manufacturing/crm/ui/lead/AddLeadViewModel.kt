package com.manufacturing.crm.ui.lead

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manufacturing.crm.data.AppDatabase
import com.manufacturing.crm.data.Lead
import com.manufacturing.crm.repository.LeadRepository
import kotlinx.coroutines.launch

class AddLeadViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = LeadRepository(
        database.leadDao(),
        database.documentDao(),
        database.activityDao()
    )
    
    private val _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean> = _isSaved
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    fun saveLead(lead: Lead) {
        viewModelScope.launch {
            try {
                val leadId = repository.insertLead(lead)
                if (leadId > 0) {
                    _isSaved.value = true
                } else {
                    _errorMessage.value = "Failed to save lead"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
} 