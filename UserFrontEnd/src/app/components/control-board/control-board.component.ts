import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {HomeConfigService} from "./home-config.service";
import {HomeConfig} from "../../shared/entity/home-config";

@Component({
  selector: 'app-control-board',
  templateUrl: './control-board.component.html',
  styleUrls: ['./control-board.component.css']
})
export class ControlBoardComponent implements OnInit {

  homeList: HomeConfig[] = [];

  constructor(private authService: AuthService, private homeService: HomeConfigService) { }

  ngOnInit(): void {

    // this.homeList.push({id:'12345',name:'asdfg'});
  }

}
