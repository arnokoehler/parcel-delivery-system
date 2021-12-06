import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {ErrorHandlerService, HandleError} from "../error-handler.service";
import {Parcel} from "./parcel";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ParcelService {
  parcelUrl = 'api/heroes';
  private handleError: HandleError;

  constructor(
      private http: HttpClient,
      httpErrorHandler: ErrorHandlerService) {
    this.handleError = httpErrorHandler.createHandleError('ParcelService');
  }

  getHeroes(): Observable<Parcel[]> {
    return this.http.get<Parcel[]>(this.parcelUrl)
        .pipe(
            catchError(this.handleError('getHeroes', []))
        );
  }
}
