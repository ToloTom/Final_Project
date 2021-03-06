package com.example.final_project

import kotlinx.coroutines.flow.Flow

class EquipmentRepository(private val equipmentDAO: EquipmentDAO) {
    val allEquipment: Flow<List<Equipment>> = equipmentDAO.getAllEquipment()

    suspend fun insertEquipment(equipment: Equipment){
        equipmentDAO.insertEquipment(equipment)
    }

    suspend fun updateEquipment(quantity: Int, id: Int){
        equipmentDAO.updateEquipment(quantity, id)
    }

    suspend fun deleteEquipment(id: Int){
        equipmentDAO.deleteEquipment(id)
    }

    fun getEquipment(id: Int): Flow<Equipment>{
        return equipmentDAO.getDream(id)
    }

}