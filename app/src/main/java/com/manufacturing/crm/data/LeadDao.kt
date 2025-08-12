package com.manufacturing.crm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LeadDao {
    @Query("SELECT * FROM leads ORDER BY createdAt DESC")
    fun getAllLeads(): LiveData<List<Lead>>
    
    @Query("SELECT * FROM leads WHERE status = :status ORDER BY createdAt DESC")
    fun getLeadsByStatus(status: LeadStatus): LiveData<List<Lead>>
    
    @Query("SELECT * FROM leads WHERE priority = :priority ORDER BY createdAt DESC")
    fun getLeadsByPriority(priority: Priority): LiveData<List<Lead>>
    
    @Query("SELECT * FROM leads WHERE companyName LIKE '%' || :searchQuery || '%' OR contactPerson LIKE '%' || :searchQuery || '%'")
    fun searchLeads(searchQuery: String): LiveData<List<Lead>>
    
    @Query("SELECT * FROM leads WHERE id = :leadId")
    suspend fun getLeadById(leadId: Long): Lead?
    
    @Insert
    suspend fun insertLead(lead: Lead): Long
    
    @Update
    suspend fun updateLead(lead: Lead)
    
    @Delete
    suspend fun deleteLead(lead: Lead)
    
    @Query("SELECT COUNT(*) FROM leads WHERE status = :status")
    suspend fun getLeadCountByStatus(status: LeadStatus): Int
    
    @Query("SELECT SUM(estimatedValue) FROM leads WHERE status = :status")
    suspend fun getTotalValueByStatus(status: LeadStatus): Double?
    
    @Query("SELECT * FROM leads WHERE expectedDeliveryDate BETWEEN :startDate AND :endDate")
    fun getLeadsByDateRange(startDate: java.util.Date, endDate: java.util.Date): LiveData<List<Lead>>
} 