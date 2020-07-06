import { Injectable } from '@angular/core';
import {ApiService} from '../services/api';
import {AnagraficaCliente} from '../../modules/admin/customer-view/model/anagrafica-cliente';
import {UtilsService} from '../utils/utils-service';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {

  constructor(private _api: ApiService) { }

    resetPassword(req): Promise<any> {
        return new Promise<any>((res, rej) => {
            this._api.get('/public/resetPassword', UtilsService.buildQueryParams(req)).subscribe(success => res(success), error => rej(error));
        });
    }
}
