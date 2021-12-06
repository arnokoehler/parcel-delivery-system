import { Component, OnInit } from '@angular/core';
import {ParcelService} from "./parcel.service";

@Component({
  selector: 'app-parcel-display',
  templateUrl: './parcel-display.component.html',
  styleUrls: ['./parcel-display.component.css']
})
export class ParcelDisplayComponent implements OnInit {

  constructor(private parcelService: ParcelService) {}

  ngOnInit(): void {
  }

}
