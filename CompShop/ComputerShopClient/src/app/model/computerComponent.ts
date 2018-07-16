import { Brand } from "./brand";

export interface ComputerComponent {
	id?: number;
	name: string;
	brand: Brand;
	price: number;
}

export class Component implements ComputerComponent{
	public id: number;
	public name: string;
	public brand: Brand;
	public price: number;
		
	constructor(component:ComputerComponent)
	{
		this.id = component.id;
		this.name = component.name;
		this.brand = component.brand;
		this.price = component.price;
	}
}
