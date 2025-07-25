import { Component, OnInit } from '@angular/core';
import {Cheef} from "../../../model/cheef";
import {CheefService} from "../../../service/cheef.service";

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {

  constructor(private cheefService: CheefService) { }

  cheef: Cheef[] = [];
  ngOnInit(): void {
  this.loadChefs()
  }

  loadChefs() {
    this.cheefService.getAllCheefs().subscribe(
      response => {
        this.cheef = response;
      }
    )
  }

}
