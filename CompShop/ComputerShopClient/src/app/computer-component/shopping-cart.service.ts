import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComputerComponent } from '../model/computerComponent';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class ShoppingCartService {

  readonly path = 'api/shoppingCart';

  constructor(private http:HttpClient) { }

  buy(component:ComputerComponent): Observable<ComputerComponent[]>{
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
		return this.http.
      //pozivamo post metodu
      post(this.path, JSON.stringify(component), { headers }).
      //iz odgovora cemo uzeti samo listu records
      map((resp)=> resp['components']).
      //ako se desi greska, prosledimo je dalje
			catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  showShoppingCart(): Observable<ComputerComponent[]> {
    return this.http.get('/api/shoppingCart')
      .map((res)=> res['components'])
      .catch ((error:any) =>
      Observable.throw (error.message || 'Server error') 
      );
  }


}
