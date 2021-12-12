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



});
