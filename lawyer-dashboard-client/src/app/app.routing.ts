import { Route } from '@angular/router';
import { AuthGuard } from 'app/core/auth/guards/auth.guard';
import { NoAuthGuard } from 'app/core/auth/guards/noAuth.guard';
import { LayoutComponent } from 'app/layout/layout.component';
import { EmptyLayoutComponent } from 'app/layout/layouts/empty/empty.component';
import { InitialDataResolver } from 'app/app.resolvers';

// @formatter:off
// tslint:disable:max-line-length
export const appRoutes: Route[] = [

    // Redirect empty path to '/example'
    {path: '', pathMatch : 'full', redirectTo: 'customer/add'},

    // Redirect signed in user to the '/example'
    {path: 'signed-in-redirect', pathMatch : 'full', redirectTo: 'customer/add'},

    // Auth routes
    {
        path: '',
        canActivate: [NoAuthGuard],
        canActivateChild: [NoAuthGuard],
        component: EmptyLayoutComponent,
        children: [
            {path: 'confirmation-required', loadChildren: () => import('app/modules/auth/confirmation-required/confirmation-required.module').then(m => m.AuthConfirmationRequiredModule)},
            {path: 'forgot-password', loadChildren: () => import('app/modules/auth/forgot-password/forgot-password.module').then(m => m.AuthForgotPasswordModule)},
            {path: 'reset-password', loadChildren: () => import('app/modules/auth/reset-password/reset-password.module').then(m => m.AuthResetPasswordModule)},
            {path: 'sign-in', loadChildren: () => import('app/modules/auth/sign-in/sign-in.module').then(m => m.AuthSignInModule)},
            {path: 'sign-up', loadChildren: () => import('app/modules/auth/sign-up/sign-up.module').then(m => m.AuthSignUpModule)}
        ]
    },
    {
        path: '',
        canActivate: [AuthGuard],
        canActivateChild: [AuthGuard],
        component: EmptyLayoutComponent,
        children: [
            {path: 'sign-out', loadChildren: () => import('app/modules/auth/sign-out/sign-out.module').then(m => m.AuthSignOutModule)},
            {path: 'unlock-session', loadChildren: () => import('app/modules/auth/unlock-session/unlock-session.module').then(m => m.AuthUnlockSessionModule)}
        ]
    },

    // Landing routes
    // {
    //     path: '',
    //     component: EmptyLayoutComponent,
    //     children   : [
    //         {path: 'home', loadChildren: () => import('app/modules/landing/home/home.module').then(m => m.LandingHomeModule)},
    //     ]
    // },
    // Landing routes
    // {
    //     path: '',
    //     canActivate: [AuthGuard],
    //     canActivateChild: [AuthGuard],
    //     component: LayoutComponent,
    //     resolve    : {
    //         initialData: InitialDataResolver,
    //     },
    //     children   : [
    //         {path: 'report', loadChildren: () => import('app/modules/admin/report-views/report-views.module').then(m => m.ReportViewsModule)},
    //     ]
    // },
    // Admin routes
    {
        path       : 'customer',
        canActivate: [AuthGuard],
        canActivateChild: [AuthGuard],
        component  : LayoutComponent,
        resolve    : {
            initialData: InitialDataResolver,
        },
        children   : [

            // Example
            {path: 'add', loadChildren: () => import('app/modules/admin/customer-view/add-customer/add-customer.module').then(m => m.AddCustomerModule)},
            {path: 'modify/:id', loadChildren: () => import('app/modules/admin/customer-view/modify-customer/modify-customer.module').then(m => m.ModifyCustomerModule)},
            {path: 'report', loadChildren: () => import('app/modules/admin/report-views/report-views.module').then(m => m.ReportViewsModule)},
            {path: 'search', loadChildren: () => import('app/modules/admin/search-report-element/search-report.module').then(m => m.SearchReportModule)},
            {path: 'search-patronato', loadChildren: () => import('app/modules/admin/search-report-element/search-report-patronato/search-report-patronato.module').then(m => m.SearchReportPatronatoModule)}

           // 404 & Catch all
           //  {path: '404-not-found', pathMatch: 'full', loadChildren: () => import('app/modules/admin/pages/errors/error-404/error-404.module').then(m => m.Error404Module)},
           //  {path: '**', redirectTo: '404-not-found'}
        ]
    }
];
