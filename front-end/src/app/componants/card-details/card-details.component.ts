import {Component, OnInit} from '@angular/core';
import {CartService} from "../../../service/cart.service";
import {CartOrder} from "../../../model/cart-order";
import {Router} from "@angular/router";
import {OrderService} from "../../../service/order.service";

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

    order: CartOrder[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private cartService: CartService, private orderService: OrderService, private router: Router) {}

  ngOnInit(): void {
    this.getOrderDetails()
  }

  getOrderDetails(){
    this.order = this.cartService.orders;
  }

  increaseOrder(order: CartOrder) {
    this.cartService.addProductToCart(order)
  }

  deCreaseOrder(order: CartOrder) {
    this.cartService.deCreaseOrder(order)
  }


  removeOrder(order: CartOrder){
    this.cartService.removeOrder(order)
  }

  createOrder(){

    const productsIds = this.cartService.orders.map(order => order.id);

    this.cartService.totalOrderPrice.subscribe(
      value => this.totalPrice = value
    )

    this.cartService.totalOrderSize.subscribe(
      value => this.totalQuantity = value
    )

    this.orderService.createOrder(this.totalPrice, this.totalQuantity, productsIds).subscribe(
      value => {
        alert("your code is " + value.code);
        this.cartService.orders = [];
        this.cartService.totalOrderSize.next(0);
        this.cartService.totalOrderPrice.next(0);
        this.router.navigateByUrl("/payment");
      },
      error => {
        // console.error("Error occurred while placing the order:", error);
        alert("Failed to place order. Please try again later.");
      }
    )
  }
}
