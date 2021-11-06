import {Component, Input, OnInit} from '@angular/core';
import {HomeConfig} from "../../shared/entity/home-config";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Input() homeConfig: HomeConfig;

  constructor() { }

  ngOnInit(): void {
  }

}
