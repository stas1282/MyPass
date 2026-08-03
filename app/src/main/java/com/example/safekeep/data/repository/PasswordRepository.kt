package com.example.safekeep.data.repository

import androidx.lifecycle.LiveData
import com.example.safekeep.data.dao.PasswordDao
import com.example.safekeep.data.entity.PasswordEntity

class PasswordRepository(private val dao: PasswordDao) {
    val allPasswords: LiveData<List<PasswordEntity>> = dao.getAll()

    fun getByType(type: String): LiveData<List<PasswordEntity>> = dao.getByType(type)

    suspend fun getById(id: Long): PasswordEntity? = dao.getById(id)

    fun search(query: String): LiveData<List<PasswordEntity>> = dao.search(query)

    suspend fun insert(entity: PasswordEntity): Long = dao.insert(entity)

    suspend fun update(entity: PasswordEntity) = dao.update(entity)

    suspend fun delete(entity: PasswordEntity) = dao.delete(entity)
}
