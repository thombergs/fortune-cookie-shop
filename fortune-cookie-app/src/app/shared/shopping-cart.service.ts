import {Injectable} from "@angular/core";
import {Product} from "./product";
import "rxjs/add/operator/map";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class ShoppingCartService {

  products: BehaviorSubject<Product[]> = new BehaviorSubject([]);

  addProduct(product: Product): void {
    let products: Product[] = this.products.getValue();
    products.push(product);
    this.products.next(products);
  }

  resetShoppingCart(): void {
    this.products.next([]);
  }

}
