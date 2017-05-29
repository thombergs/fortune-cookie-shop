import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'cents'
})
export class CentsPipe implements PipeTransform {

  transform(value: number, args?: any): any {
    return value / 100;
  }

}
