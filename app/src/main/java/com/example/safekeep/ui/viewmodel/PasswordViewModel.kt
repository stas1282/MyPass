package com.example.safekeep.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.safekeep.data.database.AppDatabase
import com.example.safekeep.data.entity.PasswordEntity
import com.example.safekeep.data.repository.PasswordRepository
import kotlinx.coroutines.launch

class PasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PasswordRepository

    private val _passwords = MutableLiveData<List<PasswordEntity>>()
    val passwords: LiveData<List<PasswordEntity>> = _passwords

    private var currentType: String = "SITE"

    init {
        val dao = AppDatabase.getInstance(application).passwordDao()
        repository = PasswordRepository(dao)
        loadByType(currentType)
    }

    fun setType(type: String) {
        currentType = type
        loadByType(type)
    }

    private fun loadByType(type: String) {
        viewModelScope.launch {
            repository.getByType(type).observeForever { list ->
                _passwords.value = list
            }
        }
    }

    fun insert(title: String, username: String, password: String, url: String, notes: String, type: String) {
        viewModelScope.launch {
            repository.insert(PasswordEntity(
                title = title,
                username = username,
                password = password,
                url = url,
                notes = notes,
                type = type
            ))
            loadByType(currentType)
        }
    }

    fun update(entity: PasswordEntity) {
        viewModelScope.launch { repository.update(entity); loadByType(currentType) }
    }

    fun delete(entity: PasswordEntity) {
        viewModelScope.launch { repository.delete(entity); loadByType(currentType) }
    }
}
