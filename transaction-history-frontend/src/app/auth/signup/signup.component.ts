import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  providers: [MessageService]
})
export class SignupComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) {}

  onSignUp() {
    const userData = { username: this.username, password: this.password };
    this.authService.signUp(userData).subscribe({
      next: (response: any) => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Sign Up Successful' });
        this.router.navigate(['/signin']);
      },
      error: (error) => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Invalid User Credentials, please try again!' });
      }
    });
  }
}
