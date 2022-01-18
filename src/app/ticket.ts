export class Ticket {
public ticket_id!: number;
public raisedby_employee_id!: number;
public type_of_issue!: string;
public raised_on!: Date;
public resolved!: boolean;
public resolved_on!: Date;
public resolved_by_id!: number
constructor(){
    
}
}
