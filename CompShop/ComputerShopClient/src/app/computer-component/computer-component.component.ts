import { Component, OnInit } from '@angular/core';
import { Page } from '../model/page'
import { ComputerComponent } from '../model/computerComponent'
import {ComputerComponentService} from './computer-component.service'
import { AuthenticationService } from '../security/authentication-service.service';
import { HttpClient } from '@angular/common/http';
import { ShoppingCartService } from './shopping-cart.service';

@Component({
  selector: 'app-computer-component',
  templateUrl: './computer-component.component.html',
  styleUrls: ['./computer-component.component.css']
})
export class ComputerComponentComponent implements OnInit {
	
  currentPageNumber: number;
  //totalPages
  page: Page<ComputerComponent>;
  public activeComponent: ComputerComponent;
  public isAdmin: boolean;
  public isBuyer: boolean;
  public chosenComponents: ComputerComponent[];	

  constructor(private http: HttpClient, private compService: ComputerComponentService, 
               private authenticationService:AuthenticationService, private shopService:ShoppingCartService) { 
              
  }

  ngOnInit() {
    this.isAdmin = this.authenticationService.getCurrentUser().roles.indexOf('ADMINISTRATOR') !== -1;
    this.isBuyer = this.authenticationService.getCurrentUser().roles.indexOf('WORKER') !== -1;
    this.resetActiveComponent();
    this.currentPageNumber = 0;
    this.loadData();
    this.chosenComponents=[];
    this.showCart();
  }

  loadData() {
  	this.compService.getComputerComponents(this.currentPageNumber)
  		.subscribe(data => {
        this.page = data;
        //this.totalPages = this.page.totalPages / this.page.numberOfElements)
  		});
  }

  changePage(i: number) {
  	this.currentPageNumber += i;
  	this.loadData();
  }

  setActiveComponent(component) {
    //this.activeComponent = component;
     var b = {};
    if(component.brand){
      b = component.brand;
    }
    this.activeComponent = {
      name:component.name,
      price:component.price,
      brand:b,
      id:component.id
    }
 
  }

  resetActiveComponent() {
    this.activeComponent = {
      name:'',
      price:0,
      brand:{}
    }
  }

  deleteComponent(id: number) {
    this.compService.deleteComponent(id).subscribe(
      ()=>{
        this.loadData();
      }
    );
  }

  editComponent(component) {
   this.compService.editComponent(component).subscribe(
     ()=>{
       this.resetActiveComponent()
       this.loadData();
     }
   );
  }

  addComponent(component) {
    this.compService.addComponent(component).subscribe(
      ()=>{
        this.loadData();
      }
    );
  }


  buy(component:ComputerComponent){
    this.shopService.buy(component).subscribe(
      (res)=>{
        this.chosenComponents = res;
      }
    );
  }

  showCart(){
    this.shopService.showShoppingCart().subscribe
      ((res) => {
        this.chosenComponents = res;
      });
  }




}
