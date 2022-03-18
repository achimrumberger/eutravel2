import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ConnectionSearchComponent } from './connection-search/connection-search.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core'
import { MAT_DATE_LOCALE } from '@angular/material/core'

@NgModule({
   imports: [
     FormsModule,
      HttpClientModule,
      BrowserModule,
      BrowserAnimationsModule,
      MatAutocompleteModule,
      MatFormFieldModule,
      MatInputModule,
      MatDatepickerModule,
      MatNativeDateModule,
      ReactiveFormsModule
   ],
   declarations: [
      AppComponent,
      ConnectionSearchComponent
   ],
   providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'en-GB' }
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
