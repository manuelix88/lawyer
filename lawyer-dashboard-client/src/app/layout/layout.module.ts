import {NgModule} from '@angular/core';
import {TreoDrawerModule} from '@treo/components/drawer';
import {LayoutComponent} from 'app/layout/layout.component';
import {EmptyLayoutModule} from 'app/layout/layouts/empty/empty.module';
import {ClassyLayoutModule} from 'app/layout/layouts/vertical/classy/classy.module';
import {SharedModule} from 'app/shared/shared.module';

const modules = [
    // Empty
    EmptyLayoutModule,
    ClassyLayoutModule
];

@NgModule({
    declarations: [
        LayoutComponent
    ],
    imports     : [
        TreoDrawerModule,
        SharedModule,
        ...modules
    ],
    exports     : [
        ...modules
    ]
})
export class LayoutModule
{
}
