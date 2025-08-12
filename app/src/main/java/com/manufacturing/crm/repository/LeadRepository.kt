package com.manufacturing.crm.repository

import com.manufacturing.crm.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class LeadRepository(private val leadDao: LeadDao, private val documentDao: DocumentDao, private val activityDao: ActivityDao) {
    
    fun getAllLeads() = leadDao.getAllLeads()
    
    fun getLeadsByStatus(status: LeadStatus) = leadDao.getLeadsByStatus(status)
    
    fun getLeadsByPriority(priority: Priority) = leadDao.getLeadsByPriority(priority)
    
    fun searchLeads(query: String) = leadDao.searchLeads(query)
    
    suspend fun getLeadById(id: Long) = withContext(Dispatchers.IO) {
        leadDao.getLeadById(id)
    }
    
    suspend fun insertLead(lead: Lead): Long = withContext(Dispatchers.IO) {
        leadDao.insertLead(lead)
    }
    
    suspend fun updateLead(lead: Lead) = withContext(Dispatchers.IO) {
        leadDao.updateLead(lead.copy(updatedAt = Date()))
    }
    
    suspend fun deleteLead(lead: Lead) = withContext(Dispatchers.IO) {
        leadDao.deleteLead(lead)
    }
    
    suspend fun getLeadCountByStatus(status: LeadStatus) = withContext(Dispatchers.IO) {
        leadDao.getLeadCountByStatus(status)
    }
    
    suspend fun getTotalValueByStatus(status: LeadStatus) = withContext(Dispatchers.IO) {
        leadDao.getTotalValueByStatus(status)
    }
    
    fun getLeadsByDateRange(startDate: Date, endDate: Date) = leadDao.getLeadsByDateRange(startDate, endDate)
    
    // Document operations
    fun getDocumentsByLeadId(leadId: Long) = documentDao.getDocumentsByLeadId(leadId)
    
    fun getDocumentsByLeadAndType(leadId: Long, documentType: DocumentType) = 
        documentDao.getDocumentsByLeadAndType(leadId, documentType)
    
    suspend fun insertDocument(document: Document) = withContext(Dispatchers.IO) {
        documentDao.insertDocument(document)
    }
    
    suspend fun deleteDocument(document: Document) = withContext(Dispatchers.IO) {
        documentDao.deleteDocument(document)
    }
    
    // Activity operations
    fun getActivitiesByLeadId(leadId: Long) = activityDao.getActivitiesByLeadId(leadId)
    
    fun getPendingActivities() = activityDao.getPendingActivities()
    
    suspend fun insertActivity(activity: Activity) = withContext(Dispatchers.IO) {
        activityDao.insertActivity(activity)
    }
    
    suspend fun markActivityAsCompleted(activityId: Long) = withContext(Dispatchers.IO) {
        activityDao.markActivityAsCompleted(activityId, Date())
    }
    
    suspend fun deleteActivity(activity: Activity) = withContext(Dispatchers.IO) {
        activityDao.deleteActivity(activity)
    }
} 