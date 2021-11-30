package io.onsite.portbase.parcel

import java.math.BigDecimal
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.support.TransactionTemplate

@DataJpaTest
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

        val parcel = parcelRepository.findBy(id)!!
        assertThat(parcel.id).isEqualTo(id)
        assertThat(parcel.weight).isEqualTo(0.22f)
        assertThat(parcel.value).isEqualTo(BigDecimal.valueOf(22))
    }
}