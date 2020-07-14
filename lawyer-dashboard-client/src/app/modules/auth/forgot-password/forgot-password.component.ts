import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TreoAnimations } from '@treo/animations';
import {ForgotPasswordService} from '../../../core/auth/forgot-password.service';

@Component({
    selector     : 'auth-forgot-password',
    templateUrl  : './forgot-password.component.html',
    styleUrls    : ['./forgot-password.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations   : TreoAnimations
})
export class AuthForgotPasswordComponent implements OnInit
{
    forgotPasswordForm: FormGroup;
    message: any;

    /**
     * Constructor
     *
     * @param {FormBuilder} _formBuilder
     */
    constructor(
        private _formBuilder: FormBuilder, private _forgotPasswordservice: ForgotPasswordService
    )
    {
        // Set the defaults
        this.message = null;
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void
    {
        // Create the form
        this.forgotPasswordForm = this._formBuilder.group({
            email: ['', [Validators.required, Validators.email]]
        });
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Send the reset link
     */
    // tslint:disable-next-line:typedef
    async sendResetLink()
    {
        // Do nothing if the form is invalid
        if ( this.forgotPasswordForm.invalid )
        {
            return;
        }

        // Disable the form
        this.forgotPasswordForm.disable();

        // Hide the message
        this.message = null;

        // Do your action here...
        await this._forgotPasswordservice.resetPassword(this.forgotPasswordForm.value)
            .then( value => {
                // Re-enable the form
                this.forgotPasswordForm.enable();

                // Reset the form
                this.forgotPasswordForm.reset({});

                // Show the message
                this.message = {
                    appearance: 'outline',
                    content   : 'Reimpostazione password inviata! Riceverai un\'email se sei registrato sul nostro sistema. (verifica la cartella SPAM)',
                    shake     : false,
                    showIcon  : false,
                    type      : 'success'
                };
            })
            .catch(error => {
                this.message = {
                    appearance: 'outline',
                    content   : error.message,
                    shake     : false,
                    showIcon  : false,
                    type      : 'error'
                };
            });
        // Emulate server delay
        // setTimeout(() => {
        //
        //     // Re-enable the form
        //     this.forgotPasswordForm.enable();
        //
        //     // Reset the form
        //     this.forgotPasswordForm.reset({});
        //
        //     // Show the message
        //     this.message = {
        //         appearance: 'outline',
        //         content   : 'Password reset sent! You\'ll receive an email if you are registered on our system.',
        //         shake     : false,
        //         showIcon  : false,
        //         type      : 'success'
        //     };
        // }, 1000);
    }
}
