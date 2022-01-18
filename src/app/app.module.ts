import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TicketDetailsComponent } from './ticket-details/ticket-details.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { UpdateTicketComponent } from './update-ticket/update-ticket.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';

@NgModule({
  declarations: [
    AppComponent,
    TicketDetailsComponent,
    TicketListComponent,
    UpdateTicketComponent,
    CreateTicketComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
