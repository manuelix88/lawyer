import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, ReplaySubject} from 'rxjs';
import {User} from '../../modules/auth/models/User';
import {ApiService} from '../services/api';
import {JwtService} from '../services/jwt.service';
import {map} from 'rxjs/operators';
import {JwtTokenData} from '../config/model/jwt-token-data';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    private currentUserSubject = new BehaviorSubject<User>({} as User);

    private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
    public isAuthenticated = this.isAuthenticatedSubject.asObservable();
    constructor(private api: ApiService, private jwtService: JwtService) { }

    signIn(credentials): Observable<User> {
        return this.api.post('/public/login', credentials).pipe(map(
            (value: User) => {
                this.jwtService.setToken(value.token);
                this.currentUserSubject.next(this.getLoggedUser(value.token));
                this.isAuthenticatedSubject.next(true);
                return value;
            }
        ));
    }

    private getLoggedUser(token: string): User {
        const serializeUser = token.split('.')[1];
        const jwtUser: JwtTokenData = JSON.parse(atob(serializeUser));
        const user: User = {username: jwtUser.sub}
        return user;
    }

    public getCurrentUser(): User {
        return this.currentUserSubject.value;
    }


}
