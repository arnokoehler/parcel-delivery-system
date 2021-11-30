package io.onsite.portbase.parcel

import java.util.UUID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ParcelRepository {

    fun insert(parcel: Parcel) {
        ParcelTable.insert {
            it[id] = parcel.id
            it[receipientId] = parcel.receipient?.id
            it[weight] = parcel.weight
            it[value] = parcel.value
        }
        parcel.receipient?.let { insertReicpient(parcel.receipient) }
    }

    private fun insertReicpient(receipient: Receipient) {
        ReceipientTable.insert {
            it[id] = receipient.id
            it[street] = receipient.address.street
            it[houseNumber] = receipient.address.houseNumber
            it[city] = receipient.address.city
        }
    }

    @Transactional(readOnly = true)
    fun findBy(id: UUID): Parcel? {
        val parcel = ParcelTable.select {
            ParcelTable.id eq id
        }
            .map { it.toParcel() }
            .singleOrNull()

        return parcel?.let {
            ReceipientTable.select {
                ReceipientTable.id eq it.receipient?.id
            }.map { it.toReceipient() }
                .map { parcel.copy(receipient = it) }
                .singleOrNull()
        }
    }
}

private fun ResultRow.toReceipient() = Receipient(
    id = this[ReceipientTable.id].value,
    name = this[ReceipientTable.name],
    address = Address(
        street = this[ReceipientTable.street],
        houseNumber = this[ReceipientTable.houseNumber],
        postalCode = this[ReceipientTable.postalCode],
        city = this[ReceipientTable.city]
    )
)

private fun ResultRow.toParcel() = Parcel(
    id = this[ParcelTable.id].value,
    receipient = null,
    weight = this[ParcelTable.weight],
    value = this[ParcelTable.value]
)

