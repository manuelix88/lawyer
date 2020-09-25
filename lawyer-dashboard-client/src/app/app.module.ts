import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ExtraOptions, PreloadAllModules, RouterModule} from '@angular/router';
import {MarkdownModule} from 'ngx-markdown';
import {TreoModule} from '@treo';
import {TreoConfigModule} from '@treo/services/config';
import {TreoMockApiModule} from '@treo/lib/mock-api';
import {CoreModule} from 'app/core/core.module';
import {appConfig} from 'app/core/config/app.config';
import {mockDataServices} from 'app/data/mock';
import {LayoutModule} from 'app/layout/layout.module';
import {AppComponent} from 'app/app.component';
import {appRoutes} from 'app/app.routing';
import {NgxSpinnerModule} from 'ngx-spinner';
import {ToastrModule} from 'ngx-toastr';

const routerConfig: ExtraOptions = {
    scrollPositionRestoration: 'enabled',
    preloadingStrategy       : PreloadAllModules
};

@NgModule({
    declarations: [
        AppComponent
    ],
    imports     : [
        BrowserModule,
        BrowserAnimationsModule,
        RouterModule.forRoot(appRoutes, routerConfig),
        ToastrModule.forRoot({
            timeOut: 10000,
            positionClass: 'toast-bottom-right',
            preventDuplicates: true,
        }),
        // Treo & Treo Mock API
        TreoModule,
        TreoConfigModule.forRoot(appConfig),
        TreoMockApiModule.forRoot(mockDataServices),

        // Core
        CoreModule,

        // Layout
        LayoutModule,
        NgxSpinnerModule,
        // 3rd party modules
        MarkdownModule.forRoot({})
    ],
    bootstrap   : [
        AppComponent
    ]
})
export class AppModule
{
}
