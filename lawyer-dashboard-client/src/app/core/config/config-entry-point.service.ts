import {APP_INITIALIZER, Injectable} from '@angular/core';
import {ConfigureApi} from './model/configure-api';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class ConfigEntryPointService {

    private _env: string;
    private _configBaseUrl = new ConfigureApi();

    constructor(private _http: HttpClient) {
    }

    async load() {
        return new Promise((resolve, reject) => {
            this._env = 'configuration';
            if (environment.production) {
                this._env = 'configuration';
            }
            this._http.get('./assets/config/' + this._env + '.json').pipe(
                map(res => {
                    return res;
                }))
                .subscribe(data => {
                        const dataObj = JSON.stringify(data);
                        this._configBaseUrl = JSON.parse(dataObj);
                        resolve(true);
                    },
                    (error: any) => {
                        console.error(error);
                        return Observable.throw(error.json().error || 'Server error');
                    });
        });
    }
    getUrlBase(): string {
        return this._configBaseUrl.api_endpoint.url;
    }

}
export function ConfigFactory(config: ConfigEntryPointService) {
    return () => config.load();
}

// tslint:disable-next-line:typedef
export function init() {
    return {
        provide: APP_INITIALIZER,
        useFactory: ConfigFactory,
        deps: [ConfigEntryPointService],
        multi: true
    };
}

const ConfigModule = {
    init: init
};

export { ConfigModule };
