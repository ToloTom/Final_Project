package com.example.final_project

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "equpment_table")
class Equipment(@ColumnInfo(name = "title") val title: String,
                @ColumnInfo(name = "type") val type: String,
                @ColumnInfo(name = "description") val info: String,
                @ColumnInfo(name = "price") val price: Double,
                @ColumnInfo(name = "quantity") val quantity: Int = 0) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0;
}
