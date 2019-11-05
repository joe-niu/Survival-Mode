
import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { GetDataService } from './get-data.service';
import { data } from './models/data.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'responders'; 
  results: any[];
  Data: data[];

  constructor(public dataService: GetDataService) {

  }

  ngOnInit() {
    this.GetData();
  }
  
  GetData() {
    this.Data = [];
    this.dataService.GetData().subscribe(res => {
      this.results = res as data[];
      console.log("results=",this.results);
      let tempData = new data();
      for (let x=0; x< this.results.length; x+=5) {
          tempData.severity = this.results[x];
          tempData.Longitude = this.results[x+1];
          tempData.Lattitude = this.results[x+2];
          tempData.FirstName = this.results[x+3];
          tempData.LastName = this.results[x+4];
          x++
          this.Data.push(tempData);
          tempData = new data();
          console.log('Data=',this.Data);
      }
    })
  }



}
