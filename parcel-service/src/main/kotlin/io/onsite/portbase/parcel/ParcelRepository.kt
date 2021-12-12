package io.onsite.portbase.parcel

import java.util.UUID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ParcelRepository {

    private val parcelWithReceipient = (ParcelTable leftJoin ReceipientTable)

    fun insert(parcel: Parcel): Parcel {
        parcel.receipient?.let { insertReicpient(it) }

        ParcelTable.insert {
            it[id] = parcel.id
            it[receipientId] = parcel.receipient?.id
            it[weight] = parcel.weight
            it[value] = parcel.value
        }
        return parcel
    }

    @Transactional(readOnly = true)
    fun findBy(id: UUID): Parcel {
        val parcel = parcelWithReceipient.select {
            ParcelTable.id eq id
        }
            .map(::mapParcelToReceipient)
            .singleOrNull() ?: throw ParcelNotFoundException("Parcel not found")

        return parcel
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Parcel> {
        return parcelWithReceipient.selectAll()
            .map(::mapParcelToReceipient)
    }

    private fun mapParcelToReceipient(it: ResultRow): Parcel {
        val parcelWithoutReceipient = it.toParcel()
        return parcelWithoutReceipient.copy(receipient = it.toReceipient())
    }

    private fun insertReicpient(receipient: Receipient) {
        ReceipientTable.insert {
            it[name] = receipient.name
            it[id] = receipient.id
            it[street] = receipient.address.street
            it[houseNumber] = receipient.address.houseNumber
            it[postalCode] = receipient.address.postalCode
            it[city] = receipient.address.city
        }
    }
}

private fun ResultRow.toReceipient(): Receipient = getOrNull(ReceipientTable.id)?.let {
    Receipient(
        id = this[ReceipientTable.id].value,
        name = this[ReceipientTable.name],
        address = Address(
            street = this[ReceipientTable.street],
            houseNumber = this[ReceipientTable.houseNumber],
            postalCode = this[ReceipientTable.postalCode],
            city = this[ReceipientTable.city]
        )
    )
} ?: throw ReceipientNotFound("Receipient with $ReceipientTable.id was not found")

private fun ResultRow.toParcel() = Parcel(
    id = this[ParcelTable.id].value,
    receipient = null,
    weight = this[ParcelTable.weight],
    value = this[ParcelTable.value]
)

class ReceipientNotFound(message: String) : Exception(message)

class ParcelNotFoundException(message: String) : Exception(message)
