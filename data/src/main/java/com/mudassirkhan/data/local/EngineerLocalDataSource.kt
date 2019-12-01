package com.mudassirkhan.data.local

import com.mudassirkhan.data.local.dao.EngineerDao
import com.mudassirkhan.data.local.model.EngineerLocalModel
import io.reactivex.Maybe
import io.reactivex.Single

class EngineerLocalDataSource (private val engineerDao: EngineerDao) {

    fun getEngineersList(): Single<List<EngineerLocalModel>> = engineerDao.getAllEngineers()

    fun insertAll(engineers: List<EngineerLocalModel>)= engineerDao.insertAll(*engineers.toTypedArray())

}