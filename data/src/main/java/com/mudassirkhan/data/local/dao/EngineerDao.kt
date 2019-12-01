package com.mudassirkhan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mudassirkhan.data.local.model.EngineerLocalModel
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface EngineerDao {

    @Query("SELECT * FROM `engineer`")
    fun getAllEngineers(): Single<List<EngineerLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg engineers:EngineerLocalModel)
}