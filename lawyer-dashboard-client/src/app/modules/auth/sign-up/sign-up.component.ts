import { Component, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { TreoAnimations } from '@treo/animations';
import { AuthService } from 'app/core/auth/auth.service';
import {TreoValidators} from '../../../../@treo/validators';

@Component({
    selector     : 'auth-sign-up',
    templateUrl  : './sign-up.component.html',
    styleUrls    : ['./sign-up.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations   : TreoAnimations
})
export class AuthSignUpComponent implements OnInit, OnDestroy
{
    message: any;
    minLength = 8;
    signUpForm: FormGroup;

    // Private
    private _unsubscribeAll: Subject<any>;

    /**
     * Constructor
     *
     * @param {AuthService} _authService
     * @param {FormBuilder} _formBuilder
     */
    constructor(
        private _authService: AuthService,
        private _formBuilder: FormBuilder
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
        // Create the form
        this.signUpForm = this._formBuilder.group({
                // name      : ['', Validators.required],
                email     : ['', [Validators.required, Validators.email]],
                password  : ['', [Validators.required, Validators.minLength(this.minLength)]],
                passwordConfirm: ['', Validators.required]
            },
            {
                validators: TreoValidators.mustMatch('password', 'passwordConfirm')
            }
                // company   : [''],
                // agreements: ['', Validators.requiredTrue]
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
     * Sign up
     */
    signUp(): void
    {
        // Do nothing if the form is invalid
        if ( this.signUpForm.invalid )
        {
            return;
        }

        // Disable the form
        this.signUpForm.disable();

        // Hide the message
        this.message = null;

        // Do your action here...

        this._authService.signUp(this.signUpForm.value).subscribe(
            value => {
                // Re-enable the form
                this.signUpForm.enable();

                // Reset the form
                this.signUpForm.reset({});

                // Show the message
                this.message = {
                    appearance: 'outline',
                    content   : 'Il tuo account è stato correttamente creato. ',
                    shake     : false,
                    showIcon  : false,
                    type      : 'success'
                };
            },
            error => {
                // Re-enable the form
                this.signUpForm.enable();

                // Reset the form
                this.signUpForm.reset({});
                // Show the message
                this.message = {
                    appearance: 'outline',
                    content   : error.message,
                    shake     : false,
                    showIcon  : false,
                    type      : 'error'
                };
            }
        );
        // Emulate server delay
        // setTimeout(() => {
        //
        //
        // }, 1000);
    }
}
