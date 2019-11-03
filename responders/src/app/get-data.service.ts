import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AsyncSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetDataService {

  constructor(private http: HttpClient) { }

  GetData() {
    let result = new AsyncSubject();
    const url = 'localhost';  

    this.http.get(url).subscribe( res => {
      result.next(res);
      result.complete();
    });
    return result;
  }
}
