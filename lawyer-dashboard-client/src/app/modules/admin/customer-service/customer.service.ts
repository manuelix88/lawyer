import { Injectable } from '@angular/core';
import {ApiService} from '../../../core/services/api';
import {AnagraficaCliente} from '../customer-view/model/anagrafica-cliente';
import {UtilsService} from '../../../core/utils/utils-service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private api: ApiService) { }

    addAnagrafica(req): Promise<any> {
        return new Promise<any>((res, rej) => {
            this.api.post('/protected/addAnagrafica', req).subscribe(success => res(success), error => rej(error));
        });
    }

    updateAnagrafica(req): Promise<any> {
        return new Promise<any>((res, rej) => {
            this.api.post('/protected/updateAnagrafica', req).subscribe(success => res(success), error => rej(error));
        });
    }

    getAnagraficaById(req): Promise<AnagraficaCliente> {
      return new Promise<AnagraficaCliente>((res, rej) => {
          this.api.get('/protected/retrieveAnagraficaById', UtilsService.buildQueryParams(req)).subscribe(success => res(success), error => rej(error));
      });
    }
    getAnagraficaAll(req): Promise<any> {
        return new Promise<any>((res, rej) => {
            this.api.get('/public/retrieveAllAnagrafica', UtilsService.buildQueryParams(req)).subscribe(success => res(success), error => rej(error));
        });
    }
}
