package com.mudassirkhan.data.mapper

import com.mudassirkhan.data.remote.entities.Engineer

class DataToDomainMapper {

    fun mapDataToDomainEngineer(dataEngineer: Engineer) = com.mudassirkhan.domain.entity.Engineer(
        id = dataEngineer.id,
        name = dataEngineer.name
    )

    fun mapDataToDomainEngineer(items: List<Engineer>) = items.map { mapDataToDomainEngineer(it) }

}