package com.mudassirkhan.data.mapper

import com.mudassirkhan.data.local.model.EngineerLocalModel
import com.mudassirkhan.data.remote.entities.Engineer

class DataToDomainMapper {

    fun mapDataToDomainEngineer(dataEngineer: Engineer) = com.mudassirkhan.domain.entity.Engineer(
        id = dataEngineer.id,
        name = dataEngineer.name
    )

    fun mapDataToDomainEngineer(items: List<Engineer>) = items.map { mapDataToDomainEngineer(it) }


    fun toLocal(dataEngineer: Engineer) = EngineerLocalModel(
        id = dataEngineer.id,
        name = dataEngineer.name
    )

    fun toLocal(items: List<Engineer>) = items.map { toLocal(it) }


    fun mapLocalToDomainEngineer(dataEngineerLocal: EngineerLocalModel) = com.mudassirkhan.domain.entity.Engineer(
        id = dataEngineerLocal.id,
        name = dataEngineerLocal.name
    )

    fun mapLocalToDomainEngineer(items: List<EngineerLocalModel>) = items.map { mapLocalToDomainEngineer(it) }

}