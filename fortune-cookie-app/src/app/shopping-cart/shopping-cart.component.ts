import {Component, OnInit} from "@angular/core";
import {ShoppingCartService} from "../shared/shopping-cart.service";
import {Product} from "../shared/product";

@Component({
  selector: '[shopping-cart]',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  products: Product[] = [];

  constructor(private shoppingCartService: ShoppingCartService) {
  }

  ngOnInit() {
    this.products = this.shoppingCartService.products;
  }

  getTotal(): number {
    return this.products.map(product => product.price).reduce((a, b) => {
      return a + b;
    }, 0);
  }

}
