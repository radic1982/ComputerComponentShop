import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppComponent } from './app.component';
import { ComputerComponentComponent } from './computer-component/computer-component.component';
import { ComputerComponentService} from './computer-component/computer-component.service';
import { CanActivateAuthGuard } from './security/can-activate-auth.guard';
import { AuthenticationService } from './security/authentication-service.service';
import { JwtUtilsService } from './security/jwt-utils.service';
import { TokenInterceptorService } from './security/token-interceptor.service';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { EditCompComponentComponent } from './edit-comp-component/edit-comp-component.component';
import { AddComponentComponent } from './add-component/add-component.component';
import { ShoppingCartService } from './computer-component/shopping-cart.service';


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'components', component: ComputerComponentComponent },
  { path: '', redirectTo: 'components', pathMatch: 'full' },
  {path: '**', component: PageNotFoundComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    ComputerComponentComponent,
    LoginComponent,
    PageNotFoundComponent,
    ShoppingCartComponent,
    EditCompComponentComponent,
    AddComponentComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      {
        enableTracing: false
      }
    )

    ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    ComputerComponentService,
    ShoppingCartService,
    CanActivateAuthGuard,
    AuthenticationService,
    JwtUtilsService,
    TokenInterceptorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
