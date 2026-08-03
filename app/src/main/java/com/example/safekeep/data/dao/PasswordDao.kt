package com.example.safekeep.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.safekeep.data.entity.PasswordEntity

@Dao
interface PasswordDao {
    @Query("SELECT * FROM passwords ORDER BY createdAt DESC")
    fun getAll(): LiveData<List<PasswordEntity>>

    @Query("SELECT * FROM passwords WHERE type = :type ORDER BY createdAt DESC")
    fun getByType(type: String): LiveData<List<PasswordEntity>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getById(id: Long): PasswordEntity?

    @Query("SELECT * FROM passwords WHERE title LIKE '%' || :query || '%'")
    fun search(query: String): LiveData<List<PasswordEntity>>

    @Insert
    suspend fun insert(entity: PasswordEntity): Long

    @Update
    suspend fun update(entity: PasswordEntity)

    @Delete
    suspend fun delete(entity: PasswordEntity)
}
