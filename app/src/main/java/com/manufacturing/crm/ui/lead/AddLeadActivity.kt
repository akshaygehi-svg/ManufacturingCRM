package com.manufacturing.crm.ui.lead

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.manufacturing.crm.data.Lead
import com.manufacturing.crm.data.LeadStatus
import com.manufacturing.crm.data.Priority
import com.manufacturing.crm.databinding.ActivityAddLeadBinding
import java.text.SimpleDateFormat
import java.util.*

class AddLeadActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddLeadBinding
    private lateinit var viewModel: AddLeadViewModel
    private var selectedDate: Date? = null
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLeadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[AddLeadViewModel::class.java]
        
        setupToolbar()
        setupSpinners()
        setupDatePicker()
        setupClickListeners()
        setupObservers()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add New Lead"
    }
    
    private fun setupSpinners() {
        // Status spinner
        val statusOptions = arrayOf("New", "Contacted", "Quotation Sent", "Negotiation", "Won", "Lost", "On Hold")
        val statusAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statusOptions)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStatus.adapter = statusAdapter
        
        // Priority spinner
        val priorityOptions = arrayOf("Low", "Medium", "High", "Urgent")
        val priorityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorityOptions)
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPriority.adapter = priorityAdapter
    }
    
    private fun setupDatePicker() {
        binding.btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                selectedDate = calendar.time
                binding.tvSelectedDate.text = dateFormat.format(selectedDate!!)
            }, year, month, day).show()
        }
    }
    
    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {
            if (validateInputs()) {
                saveLead()
            }
        }
        
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
    
    private fun setupObservers() {
        viewModel.isSaved.observe(this) { isSaved ->
            if (isSaved) {
                Toast.makeText(this, "Lead saved successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        
        viewModel.errorMessage.observe(this) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun validateInputs(): Boolean {
        var isValid = true
        
        if (binding.etCompanyName.text.toString().trim().isEmpty()) {
            binding.etCompanyName.error = "Company name is required"
            isValid = false
        }
        
        if (binding.etContactPerson.text.toString().trim().isEmpty()) {
            binding.etContactPerson.error = "Contact person is required"
            isValid = false
        }
        
        if (binding.etEmail.text.toString().trim().isEmpty()) {
            binding.etEmail.error = "Email is required"
            isValid = false
        }
        
        if (binding.etPhone.text.toString().trim().isEmpty()) {
            binding.etPhone.error = "Phone number is required"
            isValid = false
        }
        
        if (binding.etProjectDescription.text.toString().trim().isEmpty()) {
            binding.etProjectDescription.error = "Project description is required"
            isValid = false
        }
        
        val estimatedValue = binding.etEstimatedValue.text.toString().toDoubleOrNull()
        if (estimatedValue == null || estimatedValue <= 0) {
            binding.etEstimatedValue.error = "Valid estimated value is required"
            isValid = false
        }
        
        return isValid
    }
    
    private fun saveLead() {
        val status = when (binding.spinnerStatus.selectedItemPosition) {
            0 -> LeadStatus.NEW
            1 -> LeadStatus.CONTACTED
            2 -> LeadStatus.QUOTATION_SENT
            3 -> LeadStatus.NEGOTIATION
            4 -> LeadStatus.WON
            5 -> LeadStatus.LOST
            6 -> LeadStatus.ON_HOLD
            else -> LeadStatus.NEW
        }
        
        val priority = when (binding.spinnerPriority.selectedItemPosition) {
            0 -> Priority.LOW
            1 -> Priority.MEDIUM
            2 -> Priority.HIGH
            3 -> Priority.URGENT
            else -> Priority.MEDIUM
        }
        
        val lead = Lead(
            companyName = binding.etCompanyName.text.toString().trim(),
            contactPerson = binding.etContactPerson.text.toString().trim(),
            email = binding.etEmail.text.toString().trim(),
            phone = binding.etPhone.text.toString().trim(),
            address = binding.etAddress.text.toString().trim(),
            projectDescription = binding.etProjectDescription.text.toString().trim(),
            estimatedValue = binding.etEstimatedValue.text.toString().toDouble(),
            status = status,
            priority = priority,
            source = binding.etSource.text.toString().trim(),
            notes = binding.etNotes.text.toString().trim(),
            expectedDeliveryDate = selectedDate
        )
        
        viewModel.saveLead(lead)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 