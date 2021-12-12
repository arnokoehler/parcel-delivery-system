import { Component, OnInit } from '@angular/core';
import {ParcelService} from "../parcel.service";
import {Parcel} from "./parcel";

@Component({
  selector: 'app-parcel-display',
  templateUrl: './parcel-display.component.html',
  styleUrls: ['./parcel-display.component.css']
})
export class ParcelDisplayComponent implements OnInit {

  private service: ParcelService
  parcels: Array<Parcel>;

  constructor(private parcelService: ParcelService) {
    this.parcels = []
    this.service = parcelService
  }

  ngOnInit(): void {
    const self = this
    this.service.getParcels().subscribe(
        {
          next(parcel) {
            self.parcels = parcel
          }
        }
    )
  }

}
