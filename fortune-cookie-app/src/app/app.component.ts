import {Component, OnInit} from "@angular/core";
import {Product} from "./shared/product";
import {ProductService} from "./shared/product.service";
import {ShoppingCartService} from "./shared/shopping-cart.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService, private shoppingCartService: ShoppingCartService) {

  }

  ngOnInit(): void {
    this.productService.getAll().subscribe(products => this.products = products);
  }

  addToCart(product: Product): void {
    this.shoppingCartService.addProduct(product);
  }


}
