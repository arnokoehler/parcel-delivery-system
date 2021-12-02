package io.onsite.portbase.parcel

import java.math.BigDecimal
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.support.TransactionTemplate

@SpringBootTest
internal class ParcelRepositoryIT {

    @Autowired private lateinit var transactionTemplate: TransactionTemplate
    @Autowired private lateinit var parcelRepository: ParcelRepository

    @Test
    fun `should persist parcel and find by id`() {
        val id = UUID.randomUUID()
        transactionTemplate.execute {
            parcelRepository.insert(Parcel(
                id = id,
                receipient = Receipient(
                    id = UUID.randomUUID(),
                    name = "Test Tester",
                    address = Address(
                        street = "Teststraat",
                        houseNumber = 33,
                        postalCode = "2345HL",
                        city = "Testdorp"
                    )
                ),
                weight = 0.22f,
                value = BigDecimal.valueOf(22)
            ))
        }

        val parcel = parcelRepository.findBy(id)

        assertThat(parcel.id).isEqualTo(id)
        assertThat(parcel.weight.toString()).isEqualTo("0.22")
        assertThat(parcel.value.toString()).isEqualTo("22.00")
        assertThat(parcel.receipient).isNotNull
        assertThat(parcel.receipient!!.name).isEqualTo("Test Tester")
        assertThat(parcel.receipient!!.address.city).isEqualTo("Testdorp")
        assertThat(parcel.receipient!!.address.street).isEqualTo("Teststraat")
        assertThat(parcel.receipient!!.address.postalCode).isEqualTo("2345HL")
        assertThat(parcel.receipient!!.address.houseNumber).isEqualTo(33)
    }
}
