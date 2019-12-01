package com.mudassirkhan.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mudassirkhan.data.local.dao.EngineerDao
import com.mudassirkhan.data.local.model.EngineerLocalModel


@Database(entities = [EngineerLocalModel::class],version = 1,exportSchema = false)
abstract class WheelFateDatabase :RoomDatabase() {

    abstract fun engineerDao(): EngineerDao

    companion object{
        fun newInstance(context: Context): WheelFateDatabase {
            return Room.databaseBuilder(context,
                WheelFateDatabase::class.java,
                "wheel_of_fate.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}