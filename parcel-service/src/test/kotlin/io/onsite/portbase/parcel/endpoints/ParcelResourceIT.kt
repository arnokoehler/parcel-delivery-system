package io.onsite.portbase.parcel.endpoints

import io.onsite.portbase.parcel.Address
import io.onsite.portbase.parcel.Parcel
import io.onsite.portbase.parcel.ParcelRepository
import io.onsite.portbase.parcel.Receipient
import java.math.BigDecimal
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.transaction.support.TransactionTemplate

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ParcelResourceIT {

    @Autowired private lateinit var webTestClient: WebTestClient
    @Autowired private lateinit var parcelRepository: ParcelRepository
    @Autowired private lateinit var transactionTemplate: TransactionTemplate

    @Test
    fun `when searching for unknown item 404 is expected`() {
        webTestClient
            .get()
            .uri("/v1/parcels/0cfa49d9-06f9-4034-94f6-aca82624962f")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound
    }

    @Test
    fun `when searching for known id 200 ok is expected`() {
        val parcel = transactionTemplate.execute {
            parcelRepository.insert(Parcel(
                id = UUID.randomUUID(),
                receipient = Receipient(
                    id = UUID.randomUUID(),
                    name = "Test Tester",
                    address = Address(
                        street = "Teststreet",
                        houseNumber = 2,
                        postalCode = "2341AB",
                        city = "Testcity"
                    )
                ),
                weight = 2f,
                value = BigDecimal.valueOf(5)
            ))
        }

        webTestClient
            .get()
            .uri("/v1/parcels/${parcel.id}")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(ParcelResponse::class.java)
            .value<Nothing> {
                assertThat(it.size).isEqualTo(1)
                assertThat(it[0].id).isEqualTo(parcel.id)
                assertThat(it[0].weight.toString()).isEqualTo("2.0")
                assertThat(it[0].value.toString()).isEqualTo("5.0")
                assertThat(it[0].handlingDepartment).isEqualTo("REGULAR")
            }
    }


}