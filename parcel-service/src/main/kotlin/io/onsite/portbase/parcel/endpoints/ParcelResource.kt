package io.onsite.portbase.parcel.endpoints

import io.onsite.portbase.parcel.Address
import io.onsite.portbase.parcel.Department
import io.onsite.portbase.parcel.Parcel
import io.onsite.portbase.parcel.ParcelService
import java.math.BigDecimal
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/parcels/")
class ParcelResource(
    private val parcelService: ParcelService
) {

    @GetMapping("/{id}")
    fun getPayment(@PathVariable("id") id: UUID): ResponseEntity<ParcelResponse> = parcelService.getParcel(id)
        .let {
            it.toParcelResponse(
                parcelService.getHandlingDepartment(it)
            ).nullTo404()
        }
}

private fun <A> A?.nullTo404() = this
    ?.let { ResponseEntity.ok(it) }
    ?: ResponseEntity.notFound().build()

private fun Parcel.toParcelResponse(handlingDepartment: Department?): ParcelResponse = ParcelResponse(
    id = this.id,
    weight = this.weight,
    value = this.value,
    name = this.receipient!!.name,
    address = this.receipient.address,
    street = this.receipient.address.street,
    houseNumber = this.receipient.address.houseNumber,
    postalCode = this.receipient.address.postalCode,
    city = this.receipient.address.city,
    handlingDepartment = handlingDepartment!!.name
)

data class ParcelResponse(
    val id: UUID,
    val weight: Float,
    val value: BigDecimal,
    val name: String,
    val address: Address,
    val street: String,
    val houseNumber: Int,
    val postalCode: String,
    val city: String,
    val handlingDepartment: String
)
