import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-default-login-layout',
  imports: [],
  templateUrl: './default-login-layout.component.html',
  styleUrl: './default-login-layout.component.scss'
})
export class DefaultLoginLayoutComponent {

  @Input() title: string = '';
  @Input() primaryBtnText: string = '';
  @Input() secondaryBtnText: string = '';
  @Input() disabledPrimaryBtn: boolean = true;
  @Output("submit") onSumit = new EventEmitter();
  @Output("navigate") onNavigate = new EventEmitter();

submit() {
  this.onSumit.emit();  
}

navigate() {
  this.onNavigate.emit();  
}

}
