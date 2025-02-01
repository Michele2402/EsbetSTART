import {Injectable, OnDestroy} from '@angular/core';
import {NavigationStart, Router} from "@angular/router";
import {SlipService} from "./slip.service";
import {catchError, Subject, takeUntil} from "rxjs";
import {SnackbarService} from "./snackbar.service";

@Injectable({
  providedIn: 'root'
})
export class SlipSaverService implements OnDestroy {

  private validPages = ['/esports', '/competition', '/event'];
  private slipHasToBeSaved = false;

  private destroy$ = new Subject<void>();

  constructor(
    private router: Router,
    private slipService: SlipService,
    private snackBarService: SnackbarService
  ) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        this.handleNavigation(event.url);
      }
    });

    window.addEventListener('beforeunload', () => this.slipService.saveSlipInStorage());
  }

  private handleNavigation(url: string) {
    if (!this.validPages.includes(url)) {
      this.slipService.saveSlipInStorage()
        .pipe(
          takeUntil(this.destroy$),
          catchError((error) => {
            this.snackBarService.errorHandler('Failed to save slip', error);
            return [];
          })
        )
        .subscribe()
      this.slipHasToBeSaved = false;
    } else {
      this.slipHasToBeSaved = true;
    }
  }

  ngOnDestroy(): void {
  }
}
