import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class HttpTokenInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = sessionStorage.getItem('access_token')
        let request;
        const headersConfig = {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        };
        if (token) {
            headersConfig['authorization'] = `Bearer ${token}`;
        }
        request = req.clone({ setHeaders: headersConfig });
        return next.handle(request);
    }

}
