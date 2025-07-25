import {Component, OnInit} from '@angular/core';
import {CartService} from "../../../service/cart.service";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent implements OnInit {

  totalSize = 0;
  totalPrice = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.getTotal()
  }

  getTotal(){

    this.cartService.totalOrderSize.subscribe(
      data => {
        this.totalSize = data;
      }
    )

    this.cartService.totalOrderPrice.subscribe(
      data => {
        this.totalPrice = data;
      }
    )
  }

}
