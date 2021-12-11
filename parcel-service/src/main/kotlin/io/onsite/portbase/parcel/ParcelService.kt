package io.onsite.portbase.parcel

import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ParcelService(
    private val parcelRepository: ParcelRepository
) {

    @Transactional
    fun save(parcel: Parcel): Parcel {
        return parcelRepository.insert(parcel)
    }

    fun getParcel(id: UUID): Parcel {
        return parcelRepository.findBy(id)
    }

    fun getParcels(): List<Parcel> {
        return parcelRepository.findAll()
    }

    fun getHandlingDepartment(parcel: Parcel): Department? {
        return Department.valueOf(parcel.weight)
    }
}