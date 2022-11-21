import { Injectable } from '@angular/core';
import {DataService, Product} from "./data.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(public dataService: DataService) { }

  public addProduct(name: string, quantity: string, desiredQuantity: string, measurement: string, expirationDate: string, price: string) {
    let newProduct: Product = {
      id: this.dataService.getNextId(),
      name: name,
      quantity: parseInt(quantity, 10),
      desiredQuantity: parseInt(desiredQuantity, 10),
      measurement: measurement,
      expirdationDate: expirationDate,
      price: parseInt(price, 10)
    }
    this.dataService.addProduct(newProduct);
  }

  public deleteProduct(id: string) {
    this.dataService.deleteProduct(parseInt(id, 10));
  }

  public updateProduct(id: string, quantity: string, desiredQuantity: string, expirationDate: string, price: string) {
    let oldProduct = this.dataService.getProductById(parseInt(id, 10))
    let newProduct: Product = {
      id: oldProduct.id,
      name: oldProduct.name,
      quantity: parseInt(quantity, 10),
      desiredQuantity: parseInt(desiredQuantity, 10),
      measurement: oldProduct.measurement,
      expirdationDate: expirationDate,
      price: parseInt(price, 10)
    }
    this.dataService.updateProduct(newProduct);
  }
}
