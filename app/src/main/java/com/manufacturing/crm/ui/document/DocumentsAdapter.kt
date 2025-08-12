package com.manufacturing.crm.ui.document

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manufacturing.crm.data.Document
import com.manufacturing.crm.data.DocumentType
import com.manufacturing.crm.databinding.ItemDocumentBinding
import java.text.SimpleDateFormat
import java.util.*

class DocumentsAdapter(private val onDocumentClick: (Document) -> Unit) : 
    ListAdapter<Document, DocumentsAdapter.DocumentViewHolder>(DocumentDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = ItemDocumentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DocumentViewHolder(binding, onDocumentClick)
    }
    
    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class DocumentViewHolder(
        private val binding: ItemDocumentBinding,
        private val onDocumentClick: (Document) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        
        fun bind(document: Document) {
            binding.apply {
                tvFileName.text = document.fileName
                tvDocumentType.text = getDocumentTypeText(document.documentType)
                tvVersion.text = "v${document.version}"
                tvUploadDate.text = dateFormat.format(document.uploadedAt)
                tvFileSize.text = formatFileSize(document.fileSize)
                
                // Set document type icon/color
                chipDocumentType.text = getDocumentTypeText(document.documentType)
                chipDocumentType.setChipBackgroundColorResource(getDocumentTypeColor(document.documentType))
                
                root.setOnClickListener { onDocumentClick(document) }
            }
        }
        
        private fun getDocumentTypeText(documentType: DocumentType): String {
            return when (documentType) {
                DocumentType.QUOTATION -> "Quotation"
                DocumentType.DRAWING_DRAFT -> "Drawing Draft"
                DocumentType.DRAWING_REVISION -> "Drawing Revision"
                DocumentType.FINAL_QUOTATION -> "Final Quotation"
                DocumentType.TECHNICAL_SPECIFICATION -> "Technical Spec"
                DocumentType.CONTRACT -> "Contract"
                DocumentType.OTHER -> "Other"
            }
        }
        
        private fun getDocumentTypeColor(documentType: DocumentType): Int {
            return when (documentType) {
                DocumentType.QUOTATION -> android.R.color.holo_blue_light
                DocumentType.DRAWING_DRAFT -> android.R.color.holo_green_light
                DocumentType.DRAWING_REVISION -> android.R.color.holo_orange_light
                DocumentType.FINAL_QUOTATION -> android.R.color.holo_purple
                DocumentType.TECHNICAL_SPECIFICATION -> android.R.color.holo_red_light
                DocumentType.CONTRACT -> android.R.color.holo_blue_dark
                DocumentType.OTHER -> android.R.color.darker_gray
            }
        }
        
        private fun formatFileSize(sizeInBytes: Long): String {
            return when {
                sizeInBytes < 1024 -> "$sizeInBytes B"
                sizeInBytes < 1024 * 1024 -> "${sizeInBytes / 1024} KB"
                else -> "${sizeInBytes / (1024 * 1024)} MB"
            }
        }
    }
    
    class DocumentDiffCallback : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }
    }
} 