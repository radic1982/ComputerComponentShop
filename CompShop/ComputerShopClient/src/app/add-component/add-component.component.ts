import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ComputerComponent } from '../model/computerComponent';
import { Brand } from '../model/brand';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-component',
  templateUrl: './add-component.component.html',
  styleUrls: ['./add-component.component.css']
})
export class AddComponentComponent implements OnInit {
  @Output() resetAdd:EventEmitter<any>=new EventEmitter();
  @Output() addedComponent:EventEmitter<ComputerComponent>=new EventEmitter();
  private componentToAdd: ComputerComponent;
  private isDataFetched: boolean;
  private brands:Brand[];

  constructor(private http:HttpClient) {
    this.componentToAdd = {
      name:'',
      price:0,
      brand:{}
    }
   }

  ngOnInit() {
    this.loadData();
  }

  loadData(){
    var sub = this.http.get<Brand[]>('/api/brands')
    .subscribe((brand:Brand[])=>{this.brands = brand});
  }


  addComponent() {
    this.addedComponent.next(this.componentToAdd);
  }

  reset() {
    this.componentToAdd = {
      name:'',
      price:0,
      brand:{}
    }
  }



}
