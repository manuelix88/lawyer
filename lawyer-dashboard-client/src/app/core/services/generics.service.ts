import { Injectable } from '@angular/core';
import {ApiService} from './api';
import {Codice, Status, Tribunale} from '../../modules/admin/customer-view/model/report-patronato';
import {TipoPratiche} from '../../modules/admin/customer-view/model/tipo-pratiche';
import {PatronatoProvenienza} from '../../modules/admin/customer-view/model/patronatoProvenienza';
import {Avvocati} from '../../modules/admin/customer-view/model/avvocati';

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

    retrieveAllTipoPratiche(): Promise<TipoPratiche[]> {
        return new Promise<TipoPratiche[]>((res, rej) => {
            this._api.get('/public/generics/retrieveAllTipoPratiche').subscribe(success => res(success), error => rej(error));
        });
    }

    retrieveAllPatronati(): Promise<PatronatoProvenienza[]> {
        return new Promise<PatronatoProvenienza[]>((res, rej) => {
            this._api.get('/public/generics/retrieveAllPatronati').subscribe(success => res(success), error => rej(error));
        });
    }

    retrieveAllAvvocati(): Promise<Avvocati[]> {
        return new Promise<Avvocati[]>((res, rej) => {
            this._api.get('/public/generics/retrieveAllAvvocati').subscribe(success => res(success), error => rej(error));
        });
    }
}
