import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { User } from 'app/layout/common/user/user.types';
import { UserService } from 'app/layout/common/user/user.service';
import {AuthService} from '../../../core/auth/auth.service';

@Component({
    selector       : 'user',
    templateUrl    : './user.component.html',
    styleUrls      : ['./user.component.scss'],
    encapsulation  : ViewEncapsulation.None,
    changeDetection: ChangeDetectionStrategy.OnPush,
    exportAs       : 'user'
})
export class UserComponent implements OnInit, OnDestroy
{
    @Input()
    showAvatar: boolean;

    // Private
    private _unsubscribeAll: Subject<any>;
    private _user: User;
     username: string;

    /**
     * Constructor
     *
     * @param {ChangeDetectorRef} _changeDetectorRef
     * @param {Router} _router
     * @param {UserService} _userService
     * @param _auth
     */
    constructor(
        private _changeDetectorRef: ChangeDetectorRef,
        private _router: Router,
        public _userService: UserService,
        private _auth: AuthService
    )
    {
        // Set the private defaults
        this._unsubscribeAll = new Subject();

        // Set the defaults
        this.showAvatar = true;
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    /**
     * Setter and getter for user aaa
     *
     * @param value
     */
    @Input()
    set user(value: User)
    {
        // Save the user
        this._user = value;

        // Store the user in the service
        this._userService.user = value;
    }

    get user(): User
    {

        return this._user;
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void
    {
      this._auth.getCurrentUser().subscribe(
            value => {
                this.username = value.username;
            }
        );
        // Subscribe to user changes
        // this._userService.user$
        //     .pipe(takeUntil(this._unsubscribeAll))
        //     .subscribe((user: User) => {
        //         this._user = user;
        //         console.log('asdasdasd           ' + JSON.stringify(this._user));
        //     });
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void
    {
        // Unsubscribe from all subscriptions
        this._unsubscribeAll.next();
        this._unsubscribeAll.complete();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Update the user status
     *
     * @param status
     */
    updateUserStatus(status): void
    {
        // Update the user data
        this.user.status = status;

        // Update the user on the server
        this._userService.update(this.user);
    }

    /**
     * Sign out
     */
    signOut(): void
    {
        this._router.navigate(['/sign-out']);
    }
}
