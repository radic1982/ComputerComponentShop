import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { ComputerComponent } from '../model/computerComponent';
import { Brand } from '../model/brand';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-comp-component',
  templateUrl: './edit-comp-component.component.html',
  styleUrls: ['./edit-comp-component.component.css']
})
export class EditCompComponentComponent implements OnInit {

  @Input() componentToChange: ComputerComponent;
  @Output() changedComponent: EventEmitter<ComputerComponent>= new EventEmitter();
  @Output() resetEdit: EventEmitter<any> = new EventEmitter();
  @Output() addedComponent: EventEmitter<ComputerComponent> = new EventEmitter();
  private brands:Brand[];


  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.loadData();
  }

  editComponent() {
    if(this.componentToChange.id){
      //komponenta ima id, sto znaci da nije novouneta nego da se edituje, radi put
      this.changedComponent.next(this.componentToChange);
    }
    else{
      //u pitanju je novouneta kompomenta, radi post
      this.addedComponent.next(this.componentToChange);
    }
  }

  reset(){
    this.resetEdit.next();
    this.componentToChange = {
      name:'',
      price:0,
      brand:{}
    }
  }
  loadData(){
    var sub = this.http.get<Brand[]>('/api/brands')
    .subscribe((brand:Brand[])=>{this.brands = brand});
  }


}
