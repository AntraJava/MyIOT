import {Component, ElementRef, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {AuthService} from "../../service/auth.service";
import {BusService} from '../../service/bus.service';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
    private toggleButton: any;
    private sidebarVisible: boolean;
    greeting = ()=> this.authService.getCurrentUser()?.name;


    constructor(public location: Location, private element : ElementRef, private authService: AuthService, private busService:BusService) {
        this.sidebarVisible = false;
    }

    ngOnInit() {
        const navbar: HTMLElement = this.element.nativeElement;
        this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
    }
    sidebarOpen() {
        const toggleButton = this.toggleButton;
        const html = document.getElementsByTagName('html')[0];
        setTimeout(function(){
            toggleButton.classList.add('toggled');
        }, 500);
        html.classList.add('nav-open');

        this.sidebarVisible = true;
    };
    sidebarClose() {
        const html = document.getElementsByTagName('html')[0];
        this.toggleButton.classList.remove('toggled');
        this.sidebarVisible = false;
        html.classList.remove('nav-open');
    };
    sidebarToggle() {
        if (this.sidebarVisible === false) {
            this.sidebarOpen();
        } else {
            this.sidebarClose();
        }
    };
    isHome() {
        let titlee = this.location.prepareExternalUrl(this.location.path());
        if(titlee.charAt(0) === '#'){
          titlee = titlee.slice( 1 );
      }
        return titlee === '/home';
    }

    isControl() {
        let titlee = this.location.prepareExternalUrl(this.location.path());
        if(titlee.charAt(0) === '#'){
            titlee = titlee.slice( 1 );
        }
        return titlee === '/control';
    }

    signout() {
        localStorage.removeItem("iotToken");
    }

    addHome() {
        this.busService.sendMessage("new home");
    }

    addNewHome() {

    }
}
