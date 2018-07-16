import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {Page} from '../model/page';
import {ComputerComponent} from '../model/computerComponent';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class ComputerComponentService {
	
  constructor(private httpClient: HttpClient) {}

  getComputerComponents(page: number): Observable<Page<ComputerComponent>> {
  	let params = new HttpParams();
  	params = params.append('page', page.toString());

  	return this.httpClient.get('api/components', {params: params}) as 
  		Observable<Page<ComputerComponent>>;
  }

  getComponent(id:number): Observable<ComputerComponent>{
	return this.httpClient.get<ComputerComponent>(`api/components/${id}`)
    .catch((error:any)=>
    Observable.throw(error.message || 'Server Error')
    );
  }

  deleteComponent(id:number): Observable<ComputerComponent> {
    return this.httpClient.delete<ComputerComponent>(`api/components/${id}`)
    .catch((error:any)=>
    Observable.throw(error.message || 'Server Error')
    );
  }

  editComponent(component): Observable<ComputerComponent> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.put<ComputerComponent>(`api/components/${component.id}`, JSON.stringify(component), {headers})
    .catch((error:any)=>
    Observable.throw(error.message || 'Server Error')
    );
  }

  addComponent(component): Observable<ComputerComponent> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post<ComputerComponent>('api/components', JSON.stringify(component), {headers})
    .catch((error:any)=>
    Observable.throw(error.message || 'Server Error')
    );
  }

  


}
