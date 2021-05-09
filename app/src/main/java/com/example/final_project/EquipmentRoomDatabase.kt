package com.example.final_project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Equipment::class), version = 1, exportSchema = false)
public abstract class EquipmentRoomDatabase : RoomDatabase() {
    abstract fun equipmentDAO() : EquipmentDAO

    companion object{
        @Volatile
        private var INSTANCE:EquipmentRoomDatabase? = null

        fun getDatabase(context: Context): EquipmentRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, EquipmentRoomDatabase::class.java, "equipment_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}