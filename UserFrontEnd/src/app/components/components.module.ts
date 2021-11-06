import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import {NouisliderModule} from 'ng2-nouislider';
import {JwBootstrapSwitchNg2Module} from 'jw-bootstrap-switch-ng2';
import {RouterModule} from '@angular/router';

import {BasicelementsComponent} from './basicelements/basicelements.component';
import {NavigationComponent} from './navigation/navigation.component';
import {TypographyComponent} from './typography/typography.component';
import {NucleoiconsComponent} from './nucleoicons/nucleoicons.component';
import {ComponentsComponent} from './components.component';
import {NotificationComponent} from './notification/notification.component';
import {NgbdModalComponent, NgbdModalContent} from './modal/modal.component';
import {SignupComponent, SignUpModalContent} from "./signup/signup.component";
import {SigninComponent} from './signin/signin.component';
import {ControlBoardComponent} from './control-board/control-board.component';
import {DeviceComponent} from './device/device.component';
import {AddDeviceComponent} from './add-device/add-device.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        NgbModule,
        NouisliderModule,
        RouterModule,
        JwBootstrapSwitchNg2Module
    ],
    declarations: [
        ComponentsComponent,
        BasicelementsComponent,
        NavigationComponent,
        TypographyComponent,
        NucleoiconsComponent,
        NotificationComponent,
        NgbdModalComponent,
        NgbdModalContent,
        SignupComponent,
        SigninComponent,
        ControlBoardComponent,
        DeviceComponent,
        AddDeviceComponent,
        SignUpModalContent
    ],
    entryComponents: [NgbdModalContent],
    exports:[ ComponentsComponent ]
})
export class ComponentsModule { }
