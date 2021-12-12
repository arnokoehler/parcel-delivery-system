import {Component, Input, OnInit} from '@angular/core';
import {Parcel} from "../parcel";

@Component({
  selector: 'app-parcel-detail',
  templateUrl: './parcel-detail.component.html',
  styleUrls: ['./parcel-detail.component.css']
})
export class ParcelDetailComponent implements OnInit {

  @Input() parcel!: Parcel;

  constructor() { }

  ngOnInit(): void {
  }

}
