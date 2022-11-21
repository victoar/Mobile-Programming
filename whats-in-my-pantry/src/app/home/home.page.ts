import { Component } from '@angular/core';
import { RefresherCustomEvent } from '@ionic/angular';

import { DataService, Product } from '../services/data.service';
import {windowWhen} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {
  constructor(private data: DataService) { }

  refresh(ev: any) {
    setTimeout(() => {
      (ev as RefresherCustomEvent).detail.complete();
    }, 3000);
  }

  getProducts() {
    return this.data.getProducts();
  }

}
