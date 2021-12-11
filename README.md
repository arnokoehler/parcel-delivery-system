# parcel-delivery-system

### Feature 1
The current business rules are as follows:
- Parcels with a weight up to 1 kg are handled by the "Mail" departement.
- Parcels with a weight up to 10 kg are handled by the "Regular" department.
- Parcels with a weight over 10 kg are handled by the "Heavy" department.

### Setup
- Use SDK man to select openjdk 17.0.1
- Runnable Spring boot App ```src/main/kotlin/io/onsite/portbase/parcel/ParcelApplication.kt```
- goto browser ```http://localhost:8080/v1/parcels/``` to verify it works

### Startup issues 
- Somethimes with JDK 17 intellij has an issue not choosing the right Facets
- see https://www.jetbrains.com/help/idea/facet-page.html
- and select Target Platform 16 
