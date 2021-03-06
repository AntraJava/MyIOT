import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import {NouisliderModule} from 'ng2-nouislider';
import {JwBootstrapSwitchNg2Module} from 'jw-bootstrap-switch-ng2';
import {RouterModule} from '@angular/router';

import {BasicelementsComponent} from './basicelements/basicelements.component';
import {TypographyComponent} from './typography/typography.component';
import {NucleoiconsComponent} from './nucleoicons/nucleoicons.component';
import {ComponentsComponent} from './components.component';
import {NgbdModalComponent, NgbdModalContent} from './modal/modal.component';
import {SignupComponent, SignUpModalContent} from "./signup/signup.component";
import {SigninComponent} from './signin/signin.component';
import {AddHomeModalContent, ControlBoardComponent} from './control-board/control-board.component';
import {DeviceComponent} from './device/device.component';
import {AddDeviceComponent, AddDeviceModalContent} from './add-device/add-device.component';
import {HomeComponent} from './home/home.component';

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
        TypographyComponent,
        NucleoiconsComponent,
        NgbdModalComponent,
        NgbdModalContent,
        SignupComponent,
        SigninComponent,
        ControlBoardComponent,
        DeviceComponent,
        AddDeviceComponent,
        SignUpModalContent,
        AddDeviceModalContent,
        HomeComponent,
        AddHomeModalContent
    ],
    entryComponents: [NgbdModalContent],
    exports:[ ComponentsComponent ]
})
export class ComponentsModule { }
