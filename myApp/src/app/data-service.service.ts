import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { }

  removeData() {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    this.http.delete('http://localhost:8080/carsales', { headers, responseType: 'text'}).subscribe(data => {
      console.log(data);
    });
  }
}
