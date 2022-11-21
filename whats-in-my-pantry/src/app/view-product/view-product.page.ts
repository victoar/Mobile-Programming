import { Component, OnInit } from '@angular/core';
import {DataService, Product} from "../services/data.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AlertController, NavController} from "@ionic/angular";
import {ProductService} from "../services/product.service";
import {ValidatorService} from "../services/validator.service";

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.page.html',
  styleUrls: ['./view-product.page.scss'],
})
export class ViewProductPage implements OnInit {
  public product: Product | undefined;
  public quantity: string;
  public desiredQuantity: string;
  public expirationDate: string;
  public price: string;
  public notFilledError: boolean = false;
  public invalidDataError: boolean = false

  constructor(private data: DataService,
              private activatedRoute: ActivatedRoute,
              private alertController: AlertController,
              private productService: ProductService,
              private validator: ValidatorService,
              private router: Router) { }

  private id: string;

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.paramMap.get('id') as string;
    console.log(this.id)
    this.product = this.data.getProductById(parseInt(this.id, 10));
  }

  getBackButtonText() {
    const win = window as any;
    const mode = win && win.Ionic && win.Ionic.mode;
    return mode === 'ios' ? 'Inbox' : '';
  }

  async onDeleteButtonPressed() {
    const alert = await this.alertController.create({
      header: 'Are you sure you want to delete this product?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
        },
        {
          text: 'Yes',
          role: 'confirm',
          handler: () => {
            this.productService.deleteProduct(this.id);
            this.router.navigate(['/'])
          },
        },
      ],
    });

    await alert.present();
  }

  onUpdateButtonPressed() {
    this.notFilledError = this.quantity == '' || this.quantity == undefined
      || this.desiredQuantity == '' || this.desiredQuantity == undefined || this.expirationDate == ''
      || this.expirationDate == undefined || this.price == '' || this.price == undefined;

    console.log(this.quantity)
    console.log(this.desiredQuantity)
    console.log(this.price)
    console.log(this.expirationDate)

    this.invalidDataError = !(
      this.validator.onlyNumbers(this.quantity) &&
      this.validator.onlyNumbers(this.desiredQuantity) &&
      this.validator.onlyNumbers(this.price) &&
      this.validator.isItADate(this.expirationDate))

    if (this.notFilledError || this.invalidDataError)
      return;

    if(this.quantity && this.desiredQuantity && this.expirationDate && this.price) {
      this.productService.updateProduct(this.id, this.quantity, this.desiredQuantity, this.expirationDate, this.price)
      this.router.navigate(['/'])
    }
  }
}
