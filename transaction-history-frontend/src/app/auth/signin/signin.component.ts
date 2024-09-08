import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  providers: [MessageService]
})
export class SigninComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) { }

  onSignIn() {
    const credentials = { username: this.username, password: this.password };
    this.authService.signIn(credentials).subscribe({
      next: (response: any) => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Invalid User Credentials' });
      }
    });
  }
}
