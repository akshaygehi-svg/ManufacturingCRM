package com.manufacturing.crm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activities WHERE leadId = :leadId ORDER BY scheduledDate ASC, createdAt DESC")
    fun getActivitiesByLeadId(leadId: Long): LiveData<List<Activity>>
    
    @Query("SELECT * FROM activities WHERE isCompleted = 0 ORDER BY scheduledDate ASC")
    fun getPendingActivities(): LiveData<List<Activity>>
    
    @Query("SELECT * FROM activities WHERE scheduledDate BETWEEN :startDate AND :endDate")
    fun getActivitiesByDateRange(startDate: java.util.Date, endDate: java.util.Date): LiveData<List<Activity>>
    
    @Query("SELECT * FROM activities WHERE id = :activityId")
    suspend fun getActivityById(activityId: Long): Activity?
    
    @Insert
    suspend fun insertActivity(activity: Activity): Long
    
    @Update
    suspend fun updateActivity(activity: Activity)
    
    @Delete
    suspend fun deleteActivity(activity: Activity)
    
    @Query("UPDATE activities SET isCompleted = 1, completedDate = :completedDate WHERE id = :activityId")
    suspend fun markActivityAsCompleted(activityId: Long, completedDate: java.util.Date)
    
    @Query("SELECT COUNT(*) FROM activities WHERE leadId = :leadId AND isCompleted = 0")
    suspend fun getPendingActivityCount(leadId: Long): Int
} 