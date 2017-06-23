import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {CentsPipe} from "./shared/cents.pipe";
import {ProductService} from "./shared/product.service";
import {ShoppingCartService} from "./shared/shopping-cart.service";

@NgModule({
  declarations: [
    AppComponent,
    ShoppingCartComponent,
    CentsPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [ProductService, ShoppingCartService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
