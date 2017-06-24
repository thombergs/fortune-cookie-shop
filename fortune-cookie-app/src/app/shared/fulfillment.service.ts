import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Http, Response} from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class FulfillmentService {

  constructor(private http: Http) {

  }

  checkout(checkoutObject): Observable<Response> {
    return this.http.post("/fulfillment/fulfillment/shoppingCart", checkoutObject);
  }

}
