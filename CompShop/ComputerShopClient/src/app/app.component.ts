import { Component } from '@angular/core';
import { AuthenticationService } from './security/authentication-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  constructor(private authenticationService: AuthenticationService,
    private router: Router) {
  }

  logout(): void {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return this.authenticationService.isLoggedIn();
  }

}
