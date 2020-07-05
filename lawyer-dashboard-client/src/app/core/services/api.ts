import {ConfigEntryPointService} from '../config/config-entry-point.service';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    constructor( private http: HttpClient,private configService: ConfigEntryPointService) {
    }

    get(path: string, params: HttpParams = new HttpParams()): Observable<any> {
        return this.http.get(this.configService.getUrlBase().trim() + path, { params });
    }

    put(path: string, body: Object = {}): Observable<any> {
        return this.http.put(
            this.configService.getUrlBase().trim() + path,
            JSON.stringify(body)
        );
    }
    post(path: string, body: Object = {}): Observable<any> {
        return this.http.post(
            this.configService.getUrlBase().trim() + path,
            JSON.stringify(body)
        );
    }

    delete(path): Observable<any> {
        return this.http.delete(
            this.configService.getUrlBase().trim() + path
        );
    }
}
