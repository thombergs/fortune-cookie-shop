import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {ShoppingCartService} from "../shared/shopping-cart.service";
import {Product} from "../shared/product";

@Component({
  selector: '[shopping-cart]',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  @Output() checkoutClick = new EventEmitter();

  products: Product[] = [];

  total: number = 0;

  constructor(private shoppingCartService: ShoppingCartService) {
  }

  ngOnInit() {
    this.shoppingCartService.products.subscribe((products: Product[]) => {
      this.products = products;
      this.total = this.getTotal(products);
    })
  }

  getTotal(products: Product[]): number {
    return products
    .map(product => product.price)
    .reduce((a, b) => {
      return a + b;
    }, 0);
  }

  handleCheckoutClick() {
    this.checkoutClick.emit(this.createShoppingCartObject());
  }

  private createShoppingCartObject() {
    let shoppingCart = {
      user: {
        name: "Dagobert",
        surname: "Duck",
        address: "Geldspeicher",
        zipCode: "12354",
        city: "Entenhausen",
        email: "dagobert@ducks.com"
      },
      shoppingCartCookieResourceArrayList: []
    };

    for (let product of this.products) {
      shoppingCart.shoppingCartCookieResourceArrayList.push({
        cookie: {
          quote: product.quote,
          price: product.price
        },
        amount: 1
      })
    }

    return shoppingCart;
  }

}
