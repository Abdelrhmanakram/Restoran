import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  paymentData = {
    cardNumber: '',
    expiryDate: '',
    cvv: '',
    amount: 0
  };

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    const expiryDate = (document.getElementById('expiryDate') as HTMLInputElement).value;
    // console.log('Processing payment:', this.paymentData);
    this.processPayment(this.paymentData);

    this.router.navigateByUrl("/products");
  }

  processPayment(paymentData: any) {
    // console.log('Processing payment on the server:', paymentData);

    alert('Processing payment on the server')
  }

}
