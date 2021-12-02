package io.onsite.portbase.parcel

import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ParcelService(
    private val parcelRepository: ParcelRepository
) {

    fun getParcel(id: UUID): Parcel {
        return parcelRepository.findBy(id)
    }

    fun getHandlingDepartment(parcel: Parcel): Department? {
        return Department.valueOf(parcel.weight)
    }
}