import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../services/transaction.service';
import { AuthService } from '../services/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [MessageService]
})
export class DashboardComponent implements OnInit {

  transactions: any[] = [];
  searchKeyword: string = '';
  totalRecords: number = 0;

  constructor(
    private transactionService: TransactionService,
    private authService: AuthService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loadTransactions({ first: 0, rows: 10 });
  }

  onSearch() {
    this.loadTransactions({ first: 0, rows: 10 });
  }

  loadTransactions(event: any) {
    const page = event.first / event.rows;
    this.transactionService.getTransactions(this.searchKeyword, page, event.rows)
      .subscribe(response => {
        this.transactions = response.content;
        this.totalRecords = response.totalElements;
      });
  }

  onLogout() {
    this.authService.logout();
  }

  updateDescription(transaction: any) {
      this.transactionService.updateTransactionDescription(transaction.id, transaction.description).subscribe({
        next: (response: any) => {
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Transaction description updated' });
        }, error: (error) => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Update Failed, Try again' });
        }
      });
  }
}
