package com.TicketRESTTapp.controller;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TicketRESTTapp.entity.Ticket;
import com.TicketRESTTapp.repo.TicketRepository;
import com.TicketRESTTapp.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "http://localhost:4200")

public class TicketController {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private TicketService ticketService;

	@Autowired
	private TicketRepository ticketRepository;

	public TicketController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return ticketService.exportReport(format);

	}

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/findall")
	public List<Ticket> findAll() {
		return this.ticketService.findAll();
	}

	@GetMapping("/find/{ticket_id}")
	public Optional<Ticket> findById(@PathVariable int ticket_id) {
		Optional<Ticket> ticket = this.ticketService.findById(ticket_id);
		if (ticket.isPresent()) {
			return ticket;
		} else {
			return null;
		}
	}

	@GetMapping("/delete/{id}")
	public void deleteById(@PathVariable int id) {
		this.ticketService.deleteById(id);
	}

	@PostMapping("/newticket")
	public Ticket addTicket(@RequestBody Ticket ticket) {
		ticket.setTicket_id(0);
		ticket.setResolved_by_id(ticketRepository.min());
		if (ticket.isResolved()) {

			ticket.setResolved_on(Date.valueOf(LocalDate.now().plusDays(2)));
		} else {
			ticket.setResolved_on(null);
		}
		this.ticketService.addTicket(ticket);
		return ticket;
	}

	@GetMapping("/closed")
	public List<Ticket> findAllActiveTicket() {
		return this.ticketService.findAllActiveTicket();
	}

	@GetMapping("/open")
	public List<Ticket> findAllInActiveTicketNative() {
		return this.ticketService.findAllInActiveTicketNative();
	}

	@GetMapping("/raised")
	public String findRaisedDate(@RequestParam(value = "date") String date) throws JsonProcessingException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticketService.findRaisedDate(date));

	}

	@GetMapping("/resolved")
	public String findResovedDate(@RequestParam(value = "date") String date) throws JsonProcessingException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticketService.findResolvedDate(date));

	}

	@GetMapping("/raised_on")
	public HashMap<String, String> findCount(@Param("ticket") Ticket ticket) throws JsonProcessingException {
		long count = ticketService.findCount(ticket);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Raised", count + "Times");

		return hm;
	}

	@GetMapping("/resolved_on")
	public HashMap<String, String> findCountResolved(@Param("ticket") Ticket ticket) throws JsonProcessingException {
		long count = ticketService.findCountResolved(ticket);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Resolved", count + "Times");
		return hm;
	}

	@PutMapping("/update/{ticket_id}")
	public String updateTicket(@RequestBody Ticket ticket, @PathVariable("ticket_id") int ticket_id)
			throws JsonProcessingException {
		this.ticketService.updateTicket(ticket, ticket_id);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticket);
	}

	@GetMapping("/monthlyreport")
	public String findMonthlyReport(@RequestParam(value = "month") int month) throws JsonProcessingException {
		List<Ticket> result = new ArrayList();
		result = ticketService.findMonthlyReport(month);
		if (!result.isEmpty()) {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
		} else {
			return "No ticket Data available for this month";
		}

	}

}
