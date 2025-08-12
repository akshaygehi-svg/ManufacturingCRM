package com.manufacturing.crm.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "activities",
    foreignKeys = [
        ForeignKey(
            entity = Lead::class,
            parentColumns = ["id"],
            childColumns = ["leadId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Activity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val leadId: Long,
    val activityType: ActivityType,
    val title: String,
    val description: String,
    val scheduledDate: Date? = null,
    val completedDate: Date? = null,
    val isCompleted: Boolean = false,
    val createdAt: Date = Date()
)

enum class ActivityType {
    CALL,
    EMAIL,
    MEETING,
    SITE_VISIT,
    QUOTATION_SENT,
    FOLLOW_UP,
    TECHNICAL_DISCUSSION,
    NEGOTIATION,
    CLOSURE
} 