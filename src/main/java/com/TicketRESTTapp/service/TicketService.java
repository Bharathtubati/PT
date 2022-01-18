package com.TicketRESTTapp.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.TicketRESTTapp.entity.Ticket;
import com.TicketRESTTapp.repo.TicketRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class TicketService {
	private TicketRepository ticketRepository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		String path = "C:\\Users\\bhara\\OneDrive\\Desktop\\PDF";
		List<Ticket> ticket = ticketRepository.findAll();
		File file = ResourceUtils.getFile(
				"C:\\Users\\bhara\\Documents\\workspace-spring-tool-suite-4-4.11.0.RELEASE\\Ticket-RESTT-app\\src\\main\\resources\\ticket.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ticket, false);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("test", ticket);
		parameters.put("DS1", ticket);
		JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperprint,
					"C:\\Users\\bhara\\OneDrive\\Desktop\\PDF\\ticket.html");

		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperprint,
					"C:\\Users\\bhara\\OneDrive\\Desktop\\PDF\\ticket.pdf");

		}

		return "report generated in path :" + path;
	}

	public TicketService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public TicketService(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}

	public TicketRepository getTicketRepository() {
		return ticketRepository;
	}

	public void setTicketRepository(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public List<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}

	public Ticket addTicket(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}

	public Optional<Ticket> findById(int theId) {
		return this.ticketRepository.findById(theId);

	}

	public List<Ticket> findAllActiveTicket() {
		return this.ticketRepository.findAllActiveTicket();
	}

	public List<Ticket> findAllInActiveTicketNative() {
		return this.ticketRepository.findAllInActiveTicketNative();
	}

	public List<Ticket> findRaisedDate(String date) {
		return this.ticketRepository.findRaisedDate(date);
	}

	public List<Ticket> findResolvedDate(String date) {
		return this.ticketRepository.findResolvedDate(date);
	}

	public Long findCount(@Param("ticket") Ticket ticket) {
		return this.ticketRepository.findCount(ticket);
	}

	public Long findCountResolved(@Param("ticket") Ticket ticket) {
		return this.ticketRepository.findCountResolved(ticket);
	}

	public List<Ticket> findMonthlyReport(int month) {
		return this.ticketRepository.findMonthlyReport(month);
	}

	public void deleteById(int theID) {
		this.ticketRepository.deleteById(theID);
	}

	public Ticket updateTicket(Ticket ticket, int ticket_id) {
		int ticketId = ticket.getTicket_id();
		Ticket t = ticketRepository.findById(ticketId).get();
		t.setResolved_by_id(ticketRepository.min());
		t.setRaisedby_employee_id(ticket.getRaisedby_employee_id());
		t.setType_of_issue(ticket.getType_of_issue());
		t.setResolved(ticket.isResolved());
		if (t.isResolved()) {

			t.setResolved_on(Date.valueOf(LocalDate.now().plusDays(2)));
		} else {
			t.setResolved_on(null);
		}
		return ticketRepository.save(t);
	}

}
