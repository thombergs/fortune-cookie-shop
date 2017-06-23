import {Injectable} from "@angular/core";
import {Product} from "./product";
import "rxjs/add/operator/map";

@Injectable()
export class ShoppingCartService {

  products: Product[] = [];

  addProduct(product: Product): void {
    this.products.push(product);
  }

  getProductCount(): number {
    return this.products.length;
  }

}
