package com.manufacturing.crm.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "documents",
    foreignKeys = [
        ForeignKey(
            entity = Lead::class,
            parentColumns = ["id"],
            childColumns = ["leadId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Document(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val leadId: Long,
    val fileName: String,
    val filePath: String,
    val fileSize: Long,
    val documentType: DocumentType,
    val version: String = "1.0",
    val description: String = "",
    val uploadedAt: Date = Date(),
    val isActive: Boolean = true
)

enum class DocumentType {
    QUOTATION,
    DRAWING_DRAFT,
    DRAWING_REVISION,
    FINAL_QUOTATION,
    TECHNICAL_SPECIFICATION,
    CONTRACT,
    OTHER
} 