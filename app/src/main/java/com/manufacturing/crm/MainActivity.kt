package com.manufacturing.crm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.manufacturing.crm.ui.lead.LeadsFragment
import com.manufacturing.crm.ui.dashboard.DashboardFragment
import com.manufacturing.crm.ui.document.DocumentsFragment

class MainActivity : AppCompatActivity() {
    
    private lateinit var bottomNavigationView: BottomNavigationView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        
        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DashboardFragment())
                .commit()
        }
        
        bottomNavigationView.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            
            when (item.itemId) {
                R.id.nav_dashboard -> fragment = DashboardFragment()
                R.id.nav_leads -> fragment = LeadsFragment()
                R.id.nav_documents -> fragment = DocumentsFragment()
            }
            
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
        }
    }
} 