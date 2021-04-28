package com.example.final_project

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class EquipmentViewModel(private val repository: EquipmentRepository) : ViewModel() {
    val allEquipment: LiveData<List<Equipment>> = repository.allEquipment.asLiveData()

    fun insert(equipment: Equipment) = viewModelScope.launch {
        repository.insertEquipment(equipment)
    }

    fun updateEquipment(title: String, type: String, description: String, price: Double, id: Int) = viewModelScope.launch {
        repository.updateEquipment(title, type, description, price, id)
    }

    fun deleteEquipment(id: Int) = viewModelScope.launch {
        repository.deleteEquipment(id)
    }

    fun getEquipment(id: Int) = repository.getEquipment(id).asLiveData()
}

class DreamViewModelFactory(private val repository: EquipmentRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EquipmentViewModel::class.java)){
            return EquipmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}