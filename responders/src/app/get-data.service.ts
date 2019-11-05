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
    const url = 'http://127.0.0.1:9000/survival-mode';  

    this.http.get(url).subscribe( res => {
      result.next(res);
      result.complete();
    }); 
    return result;
  }
}
