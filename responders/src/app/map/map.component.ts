/// <reference types="@types/googlemaps" />
import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { ViewChild } from '@angular/core';
import { GetDataService } from '../get-data.service';
import { data } from '../models/data.model';



@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnChanges {
  @ViewChild('gmap', {static: false}) gmapElement: any;
  @Input() results:data[];
  map: google.maps.Map;

  constructor(public dataService: GetDataService) { }

  latitude:number;
  longitude:number;

  ngOnChanges() {
    if (this.results.length === 0) {
      return;
    }
    let lat = this.results[0].Lattitude;
    let long = this.results[0].Longitude;
    console.log('data=',this.results);
    var mapProp = {
      center: new google.maps.LatLng(Number(lat), Number(long)),
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
    this.setmarkers();

  }

  setmarkers() {
    for(let result of this.results) {
      var marker = new google.maps.Marker({
        position: new google.maps.LatLng(Number(result.Lattitude), Number(result.Longitude)),
        map: this.map,
        title: 'Hello World!'
      });
    }


  }





  setCenter(e:any){
    e.preventDefault();
    this.map.setCenter(new google.maps.LatLng(Number(this.results[0].Lattitude), Number(this.results[0].Longitude)));
  }


}









