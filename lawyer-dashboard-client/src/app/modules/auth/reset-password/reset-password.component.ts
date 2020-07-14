import { Component, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { TreoAnimations } from '@treo/animations';
import { TreoValidators } from '@treo/validators';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../../core/auth/auth.service';

@Component({
    selector     : 'auth-reset-password',
    templateUrl  : './reset-password.component.html',
    styleUrls    : ['./reset-password.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations   : TreoAnimations
})
export class AuthResetPasswordComponent implements OnInit, OnDestroy
{
    username: string;
    message: any;
    resetPasswordForm: FormGroup;

    // Private
    private _unsubscribeAll: Subject<any>;

    /**
     * Constructor
     *
     * @param {FormBuilder} _formBuilder
     * @param _activatedRouter
     * @param _auth
     * @param route
     */
    constructor(
        private _formBuilder: FormBuilder,private _activatedRouter: ActivatedRoute,
        private _auth: AuthService, private _route: Router
    )
    {
        // Set the defaults
        this.message = null;

        // Set the private defaults
        this._unsubscribeAll = new Subject();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void
    {

        this._activatedRouter.queryParams.subscribe(params => {
            this.username = params['id'];
        });
        // Create the form
        this.resetPasswordForm = this._formBuilder.group({
                password       : ['', Validators.required],
                passwordConfirm: ['', Validators.required]
            },
            {
                validators: TreoValidators.mustMatch('password', 'passwordConfirm')
            }
        );
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
     * Reset password
     */
    resetPassword(): void
    {
        // Do nothing if the form is invalid
        if ( this.resetPasswordForm.invalid )
        {
            return;
        }

        // Disable the form
        this.resetPasswordForm.disable();

        // Hide the message
        this.message = null;

        // Do your action here...

        const req = {
            email: this.username,
            password: this.resetPasswordForm.get('password').value
        }

        this._auth.saveNewPassword(req).pipe().subscribe(
            value => {
                this.message = {
                    appearance: 'outline',
                    content   : 'La tua password è stata modificata verrai reindirizzato alla pagina di login entro 5 secondi.',
                    shake     : false,
                    showIcon  : false,
                    type      : 'success'
                };

                // Re-enable the form
                this.resetPasswordForm.enable();

                // Reset the form
                this.resetPasswordForm.reset({});
                setTimeout(() => {
                    this._route.navigate(['sign-in']);
                }, 5000);
            }
        );
        // Emulate server delay
        // setTimeout(() => {
        //
        //     // Re-enable the form
        //     this.resetPasswordForm.enable();
        //
        //     // Reset the form
        //     this.resetPasswordForm.reset({});
        //
        //     // Show the message
        //     this.message = {
        //         appearance: 'outline',
        //         content   : 'Your password has been reset.',
        //         shake     : false,
        //         showIcon  : false,
        //         type      : 'success'
        //     };
        // }, 1000);
    }
}
