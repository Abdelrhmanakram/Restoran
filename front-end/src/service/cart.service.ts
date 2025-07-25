import { Injectable } from '@angular/core';
import {CartOrder} from "../model/cart-order";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  orders: CartOrder[] = [];
  totalOrderSize: Subject<number> = new BehaviorSubject<number>(0);
  totalOrderPrice: Subject<number> = new BehaviorSubject<number>(0);

  constructor() { }

  addProductToCart(order: CartOrder) {
    let isExsit: boolean = false;
    let exsitedOrder: CartOrder = undefined;

    if (this.orders.length !== 0){
      exsitedOrder= this.orders.find(o => o.id == order.id);
    }

    isExsit = (exsitedOrder != undefined);

    if(isExsit){
     exsitedOrder.quantity++;
    }else {
      this.orders.push(order);
    }
    this.calculateTotals()
  }

  calculateTotals(){
    let totalNumber: number =0;
    let totalPrice: number =0;

    for (let temp of this.orders){
      totalNumber += temp.quantity;
      totalPrice += temp.quantity * temp.price;
    }

    this.totalOrderSize.next(totalNumber);
    this.totalOrderPrice.next(totalPrice);
  }

  removeOrder(order: CartOrder) {
    const index = this.orders.findIndex(o => o.id === order.id); //1

    if (index > -1){
      this.orders.splice(index, 1);
    }

    this.calculateTotals()
  }


  deCreaseOrder(order: CartOrder) {
    order.quantity--;

    if (order.quantity === 0){
      this.removeOrder(order)
    } else {
      this.calculateTotals()
    }
  }
}
