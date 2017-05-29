import {Component, Input, OnInit} from "@angular/core";
import {Product} from "../shared/product";

@Component({
  selector: 'product-tile',
  templateUrl: './product-tile.component.html',
  styleUrls: ['./product-tile.component.css']
})
export class ProductTileComponent implements OnInit {

  @Input() product: Product;

  constructor() {
  }

  ngOnInit() {
  }

}
