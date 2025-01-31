import {MatSnackBar} from '@angular/material/snack-bar';
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class SnackbarService {

  constructor(
    private snackBar: MatSnackBar
  ) {
  }

  showSnackbarMessage(message: string, panelClass: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      panelClass: [panelClass],
      horizontalPosition: 'center',
      verticalPosition: 'top'
    });
  }

  errorHandler(defaultMessage: string, error: any) {

    console.log(error)
    let errorMessage = defaultMessage;
    if (error.status === 401) {
      errorMessage = "You are not authorized to perform this action";
    }

    if (error && error.error && error.error.errors) {
      errorMessage = error.error.errors
    }

    this.showSnackbarMessage(
      errorMessage, 'error-snackbar'
    );
  }
}
