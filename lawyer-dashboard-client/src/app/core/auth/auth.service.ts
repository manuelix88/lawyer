import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable, of, ReplaySubject, throwError} from 'rxjs';
import {catchError, map, switchMap} from 'rxjs/operators';
import { AuthUtils } from 'app/core/auth/auth.utils';
import {User} from '../../modules/auth/models/User';
import {JwtTokenData} from '../config/model/jwt-token-data';
import {ApiService} from '../services/api';
import {JwtService} from '../services/jwt.service';

@Injectable()
export class AuthService
{
    // Private
    private _authenticated: boolean;

    private currentUserSubject = new BehaviorSubject<User>({} as User);

    private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
    public isAuthenticated = this.isAuthenticatedSubject.asObservable();
    /**
     * Constructor
     *
     * @param {HttpClient} _httpClient
     * @param _api
     * @param jwtService
     */
    constructor(
        private _httpClient: HttpClient, private _api: ApiService, private jwtService: JwtService
    )
    {
        // Set the defaults
        this._authenticated = false;
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    /**
     * Setter and getter for access token
     */
    set accessToken(token: string)
    {
        sessionStorage.setItem('access_token', token);
    }

    get accessToken(): string
    {
        return sessionStorage.getItem('access_token');
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Sign in
     *
     * @param credentials
     */
    // signIn(credentials: { username: string, password: string }): Observable<any>
    // {
    //     // Throw error, if the user is already logged in
    //     if ( this._authenticated )
    //     {
    //         return throwError('User is already logged in.');
    //     }
    //
    //     return this._httpClient.post('http://localhost:8080/public/login', credentials).pipe(
    //         switchMap((response: any) => {
    //
    //             console.log(response.headers.get('X-Auth'));
    //             // Store the access token in the local storage
    //             this.accessToken = response.headers.get('X-Auth');
    //
    //             // Set the authenticated flag to true
    //             this._authenticated = true;
    //
    //             // Return a new observable with the response
    //             return of(response);
    //         })
    //     );
    // }

    signUp(credentials): Observable<any> {
        return this._api.post('/public/registration', credentials).pipe(map(
            (value) => {
                return value;
            }
        ));
    }

    signIn(credentials): Observable<User> {
        return this._api.post('/public/login', credentials).pipe(switchMap(
            value => {
                this._authenticated = true;
                this.jwtService.setToken(value.token);
                this.currentUserSubject.next(this.getLoggedUser(value.token));
                this.isAuthenticatedSubject.next(true);
                return of (value);
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
    /**
     * Sign in using the access token
     */
    signInUsingToken(): Observable<any>
    {

        return of(true);
    }

    /**
     * Sign out
     */
    signOut(): Observable<any>
    {
        // Remove the access token from the local storage
        sessionStorage.removeItem('access_token');

        // Set the authenticated flag to false
        this._authenticated = false;

        // Return the observable
        return of(true);
    }

    /**
     * Check the authentication status
     */
    check(): Observable<boolean>
    {
        // Check if the user is logged in
        if ( this._authenticated )
        {
            return of(true);
        }

        // Check the access token availability
        if ( !this.accessToken )
        {
            return of(false);
        }

        // // Check the access token expire date
        // if ( AuthUtils.isTokenExpired(this.accessToken) )
        // {
        //     return of(false);
        // }

        // If the access token exists and it didn't expire, sign in using it
        return this.signInUsingToken();
    }
}
