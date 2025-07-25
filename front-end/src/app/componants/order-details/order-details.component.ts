import { Component, OnInit } from '@angular/core';
import {OrderDetails} from "../../../model/order-details";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../../service/order.service";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  details: OrderDetails = null;
  allOrderDetails: OrderDetails[] =[];
  constructor(private activatedRoute: ActivatedRoute, private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      () => this.orderDetails()
    )
  }

  orderDetails(){
    const role = this.getUserRole();

    if (role === 'ROLE_ADMIN') {
      this.orderService.getAllRequestOrders().subscribe(value => {
        this.allOrderDetails = value;
      });
    } else if (role === 'ROLE_USER') {
      this.orderService.getAllUserOrders().subscribe(value => {
        this.allOrderDetails = value;
      });
    }
  }

    // let codeExist = this.activatedRoute.snapshot.paramMap.has("code");
    // if (codeExist)  {
    //   let code = this.activatedRoute.snapshot.paramMap.get("code");
      // if (code === 'user') {
        // this.orderService.getRequestOrdersRelatedToUser().subscribe(
        //   value => {
        //     this.allOrderDetails = value;
        //   }
        // )
      // } else if (code == 'allUser') {
      //   this.orderService.getAllRequestOrders().subscribe(
      //     value => {
      //       this.allOrderDetails = value;
      //     }
      //   )
      // } else if (code) {
        // this.orderService.getOrderDetails(code).subscribe(
        //   value => {
        //     this.details = new OrderDetails();
        //     this.details = value;
        //   }
        // )
      // }
  //   } else {
  //     this.router.navigateByUrl("/products")
  //   }
  // }


  getUserRole(): string | null {
    const token = sessionStorage.getItem('token');
    if (token) {
      try {
        const decodedToken = this.decodeToken(token);

        if (decodedToken?.roles && decodedToken.roles.length > 0) {
          return decodedToken.roles[0]?.code || null;
        }

        return null;
      } catch (error) {
        console.error('Error decoding token:', error);
        return null;
      }
    }
    return null;
  }


  decodeToken(token: string): any {
    try {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));

      return JSON.parse(jsonPayload);
    } catch (error) {
      throw new Error('Invalid token format');
    }
  }
}
