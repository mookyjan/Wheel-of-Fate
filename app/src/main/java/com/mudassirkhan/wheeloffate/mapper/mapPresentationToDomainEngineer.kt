package com.mudassirkhan.wheeloffate.mapper

import com.mudassirkhan.domain.entity.Engineer as DomainEngineer
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer as PresentationEngineer


fun mapPresentationToDomainEngineer(presentationEngineer: PresentationEngineer) = DomainEngineer(
    id = presentationEngineer.id,
    name = presentationEngineer.name
)

fun mapPresentationToDomainEngineer(presentationEngineersList: List<PresentationEngineer>) =
    presentationEngineersList.map { mapPresentationToDomainEngineer(it) }


fun mapDomainToPresentationEngineer(domainEngineer: DomainEngineer) = PresentationEngineer(
    id = domainEngineer.id, name = domainEngineer.name
)

fun mapDomainToPresentationEngineer(domainEngineersList: List<DomainEngineer>) =
    domainEngineersList.map {
        mapDomainToPresentationEngineer(it)
    }