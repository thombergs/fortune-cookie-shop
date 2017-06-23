import {Injectable} from "@angular/core";
import {Product} from "./product";
import {Observable} from "rxjs/Observable";
import {Http} from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class ProductService {

  constructor(private http: Http) {

  }

  getAll(): Observable<Product[]> {
    return this.http.get("/products/product/fortuneCookieList").map(response => <Product[]> response.json());
  }

}
