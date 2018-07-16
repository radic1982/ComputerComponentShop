import { Component, OnInit, Input } from '@angular/core';
import { ComputerComponent } from '../model/computerComponent';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  @Input() chosenComponents: ComputerComponent[];

  constructor() { }

  ngOnInit() {
  }

}
