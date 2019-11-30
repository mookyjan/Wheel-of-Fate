package com.mudassirkhan.wheeloffate.mapper

import com.mudassirkhan.domain.entity.Engineer as DomainEngineer
import com.mudassirkhan.wheeloffate.ui.engineerlist.model.Engineer as PresentationEngineer



    fun mapPresentationToDomainEngineer(presentationEngineer: PresentationEngineer) = DomainEngineer(
        id = presentationEngineer.id,
        name = presentationEngineer.name
    )

    fun mapPresentationToDomainEngineer(items: List<PresentationEngineer>) = items.map { mapPresentationToDomainEngineer(it) }


fun mapDomainToPresentationEngineer(domainEngineers : List<DomainEngineer>) : List<PresentationEngineer> {
    var presentationnEngineers = ArrayList<PresentationEngineer>()

    for(domainEngineer in domainEngineers) {
        val domainEngineer = PresentationEngineer(id = domainEngineer.id, name = domainEngineer.name)
        presentationnEngineers.add(domainEngineer)
    }

    return presentationnEngineers
}