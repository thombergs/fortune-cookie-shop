import {Component, OnInit} from "@angular/core";
import {Product} from "./shared/product";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  products: Product[][];

  ngOnInit(): void {
    this.products = [
      [
        {quote: "Hello", priceInCents: 101},
        {quote: "Hello2", priceInCents: 50},
        {quote: "Hell3", priceInCents: 75},
        {quote: "Hell3", priceInCents: 75},
      ],
      [
        {quote: "foo", priceInCents: 101},
        {quote: "bar", priceInCents: 50},
      ]
    ];
  }


}
