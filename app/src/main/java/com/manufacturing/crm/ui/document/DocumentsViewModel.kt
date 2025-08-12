package com.manufacturing.crm.ui.document

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manufacturing.crm.data.AppDatabase
import com.manufacturing.crm.data.Document
import com.manufacturing.crm.data.DocumentType
import com.manufacturing.crm.repository.LeadRepository

class DocumentsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository = LeadRepository(
        database.leadDao(),
        database.documentDao(),
        database.activityDao()
    )
    
    private val _documents = MutableLiveData<List<Document>>()
    val documents: LiveData<List<Document>> = _documents
    
    init {
        loadAllDocuments()
    }
    
    fun loadAllDocuments() {
        // This would need to be implemented to load all documents across all leads
        // For now, we'll use a placeholder
        _documents.value = emptyList()
    }
    
    fun filterDocumentsByType(documentType: DocumentType) {
        // This would filter documents by type across all leads
        // Implementation would depend on how you want to handle this
    }
    
    fun searchDocuments(query: String) {
        if (query.isBlank()) {
            loadAllDocuments()
        } else {
            // Search implementation
        }
    }
} 