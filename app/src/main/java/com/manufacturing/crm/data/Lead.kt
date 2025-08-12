package com.manufacturing.crm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "leads")
data class Lead(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val companyName: String,
    val contactPerson: String,
    val email: String,
    val phone: String,
    val address: String,
    val projectDescription: String,
    val estimatedValue: Double,
    val status: LeadStatus = LeadStatus.NEW,
    val source: String = "",
    val notes: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val expectedDeliveryDate: Date? = null,
    val priority: Priority = Priority.MEDIUM
)

enum class LeadStatus {
    NEW,
    CONTACTED,
    QUOTATION_SENT,
    NEGOTIATION,
    WON,
    LOST,
    ON_HOLD
}

enum class Priority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT
} 