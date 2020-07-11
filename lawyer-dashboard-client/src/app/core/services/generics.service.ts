import { Injectable } from '@angular/core';
import {ApiService} from './api';
import {Codice, Status, Tribunale} from '../../modules/admin/customer-view/model/report-patronato';

@Injectable({
  providedIn: 'root'
})
export class GenericsService {

  constructor(private _api: ApiService) { }


    getAllStatus(): Promise<Status[]> {
        return new Promise<Status[]>((res, rej) => {
            this._api.get('/public/generics/retrieveAllStatus').subscribe(success => res(success), error => rej(error));
        });
    }

    getAllTribunali(): Promise<Tribunale[]> {
        return new Promise<Tribunale[]>((res, rej) => {
            this._api.get('/public/generics/retrieveAllTribunali').subscribe(success => res(success), error => rej(error));
        });
    }

    getAllCodice(): Promise<Codice[]> {
        return new Promise<Codice[]>((res, rej) => {
            this._api.get('/public/generics/retrieveAllCode').subscribe(success => res(success), error => rej(error));
        });
    }
}
