import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Router} from "@angular/router";

@Injectable()
export class ServerErrorInterceptor implements HttpInterceptor {


    constructor(private router: Router) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            // retry(1),
            catchError((error: HttpErrorResponse) => {
                if(error.status === 401 || error.status === 500){
                    this.router.navigate(['/home']);
                }
                return throwError(error);
            })
        );
    }
}
