import { Component, OnInit, Input } from '@angular/core';
import { data } from '../models/data.model';
import { GetDataService } from '../get-data.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {
  @Input() Data:data;

  constructor(public dataService: GetDataService) { }

  ngOnInit() {

  }

}
