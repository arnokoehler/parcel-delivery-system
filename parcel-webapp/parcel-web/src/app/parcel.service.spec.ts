import {TestBed} from '@angular/core/testing';

import {ParcelService} from './parcel.service';
import {HttpClient} from "@angular/common/http";
import {Parcel} from "./parcel-display/parcel";
import {of} from "rxjs";

describe('ParcelService', () => {
    let httpClientSpy: jasmine.SpyObj<HttpClient>;
    let parcelService: ParcelService;

    beforeEach(() => {
        httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
        TestBed.configureTestingModule({});
        parcelService = TestBed.inject(ParcelService);
    });

    it('should be created', () => {
        expect(parcelService).toBeTruthy();
    });

    it('should return expected parcels (HttpClient called once)', (done: DoneFn) => {
        const expectedParcels: Parcel[] =
            [{
                id: "d360233f-f558-4144-a76e-e605004f1be6",
                name: "Koehler",
                weight: 0.14,
                value: 120,
                street: "Pijennburg",
                houseNumber: 33,
                postalCode: "3456JB",
                city: "vleuten"
            }, {
                id: "43bf9eab-496b-4f1d-8155-ed369e31c6e5",
                name: "Koehler",
                weight: 1.5,
                value: 100,
                street: "Pijennburg",
                houseNumber: 33,
                postalCode: "3456JB",
                city: "vleuten"
            }];

        httpClientSpy.get.and.returnValue(of(expectedParcels));

        var testParcels : Parcel[]
        parcelService.getParcels()
            .subscribe({
                    next : (parcel) =>
                        testParcels = parcel
                }
            )

        expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
    });


});
