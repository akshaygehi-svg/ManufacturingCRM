package com.manufacturing.crm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DocumentDao {
    @Query("SELECT * FROM documents WHERE leadId = :leadId ORDER BY uploadedAt DESC")
    fun getDocumentsByLeadId(leadId: Long): LiveData<List<Document>>
    
    @Query("SELECT * FROM documents WHERE leadId = :leadId AND documentType = :documentType ORDER BY uploadedAt DESC")
    fun getDocumentsByLeadAndType(leadId: Long, documentType: DocumentType): LiveData<List<Document>>
    
    @Query("SELECT * FROM documents WHERE id = :documentId")
    suspend fun getDocumentById(documentId: Long): Document?
    
    @Insert
    suspend fun insertDocument(document: Document): Long
    
    @Update
    suspend fun updateDocument(document: Document)
    
    @Delete
    suspend fun deleteDocument(document: Document)
    
    @Query("DELETE FROM documents WHERE leadId = :leadId")
    suspend fun deleteDocumentsByLeadId(leadId: Long)
    
    @Query("SELECT COUNT(*) FROM documents WHERE leadId = :leadId AND documentType = :documentType")
    suspend fun getDocumentCountByType(leadId: Long, documentType: DocumentType): Int
    
    @Query("SELECT * FROM documents WHERE fileName LIKE '%' || :searchQuery || '%'")
    fun searchDocuments(searchQuery: String): LiveData<List<Document>>
} 