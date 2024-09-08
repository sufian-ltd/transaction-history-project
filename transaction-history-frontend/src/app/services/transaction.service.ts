import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL, TRANSACTIONS } from '../core/constant';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  
  private apiURL = BASE_URL + TRANSACTIONS;

  constructor(private http: HttpClient) { }

  getTransactions(keyword: string, page: number, size: number): Observable<any> {
    let params = new HttpParams()
      .set('keyword', keyword)
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(this.apiURL, { params });
  }

  updateTransactionDescription(id: number, description: string): Observable<any> {
    const params = new HttpParams().set('description', description);
    return this.http.put(`${this.apiURL}/${id}`, null, { params });
  }
}
