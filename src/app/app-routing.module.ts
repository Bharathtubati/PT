import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { UpdateTicketComponent } from './update-ticket/update-ticket.component';

const routes: Routes = [
  { path:'all', component: TicketListComponent},
  { path:'add',component:CreateTicketComponent},
  { path:'update/:ticket_id',component:UpdateTicketComponent},
  { path:'',redirectTo:'all',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
