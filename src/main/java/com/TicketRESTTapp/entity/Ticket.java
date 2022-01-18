package com.TicketRESTTapp.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ticket")

public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	int ticket_id;
	@Column(name = "raisedby_employee_id")
	int raisedby_employee_id;
	@Column(name = "type_of_issue")
	String type_of_issue;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "raised_on")
	Date raised_on = Date.valueOf(LocalDate.now());
	@Column(name = "resolved")
	boolean resolved;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "resolved_on")
	Date resolved_on = Date.valueOf(LocalDate.now());
	@Column(name = "resolved_by_id")
	int resolved_by_id;

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticket_id, int raisedby_employee_id, String type_of_issue, Date raised_on, boolean resolved,
			Date resolved_on, int resolved_by_id) {
		super();
		this.ticket_id = ticket_id;
		this.raisedby_employee_id = raisedby_employee_id;
		this.type_of_issue = type_of_issue;
		this.raised_on = raised_on;
		this.resolved = resolved;
		this.resolved_on = resolved_on;
		this.resolved_by_id = resolved_by_id;
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getRaisedby_employee_id() {
		return raisedby_employee_id;
	}

	public void setRaisedby_employee_id(int raisedby_employee_id) {
		this.raisedby_employee_id = raisedby_employee_id;
	}

	public String getType_of_issue() {
		return type_of_issue;
	}

	public void setType_of_issue(String type_of_issue) {
		this.type_of_issue = type_of_issue;
	}

	public Date getRaised_on() {
		return raised_on;
	}

	public void setRaised_on(Date raised_on) {
		this.raised_on = raised_on;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public Date getResolved_on() {
		return resolved_on;
	}

	public void setResolved_on(Date resolved_on) {
		this.resolved_on = resolved_on;
	}

	public int getResolved_by_id() {
		return resolved_by_id;
	}

	public void setResolved_by_id(int resolved_by_id) {
		this.resolved_by_id = resolved_by_id;
	}

}
