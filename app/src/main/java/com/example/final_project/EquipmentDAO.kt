package com.example.final_project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDAO {
    @Query("SELECT * FROM equpment_table ORDER BY id ASC")
    fun getAllEquipment() : Flow<List<Equipment>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEquipment(equipment: Equipment)

    @Query("UPDATE equpment_table SET quantity =:quantity WHERE id=:id")
    suspend fun updateEquipment(quantity: Int, id: Int)

    @Query("DELETE FROM equpment_table WHERE id=:id")
    suspend fun deleteEquipment(id: Int)

    @Query("SELECT * FROM equpment_table WHERE id=:id")
    fun getDream(id: Int) : Flow<Equipment>
}