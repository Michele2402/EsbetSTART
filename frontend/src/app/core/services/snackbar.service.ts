import { MatSnackBar } from '@angular/material/snack-bar';
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class SnackbarService {

  constructor(private snackBar: MatSnackBar) { }

  showSnackbarMessage(message: string, panelClass: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      panelClass: [panelClass],
      horizontalPosition: 'center',
      verticalPosition: 'top'
    });
  }
}
