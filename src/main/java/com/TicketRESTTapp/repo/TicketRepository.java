package com.TicketRESTTapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TicketRESTTapp.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	@Query(value = "SELECT * FROM Ticket ticket WHERE ticket.resolved=1", nativeQuery = true)
	List<Ticket> findAllActiveTicket();

	@Query(value = "SELECT * FROM Ticket ticket WHERE ticket.resolved=0", nativeQuery = true)
	List<Ticket> findAllInActiveTicketNative();

	@Query(value = "SELECT * FROM ticket WHERE raised_on=:date", nativeQuery = true)
	List<Ticket> findRaisedDate(@Param("date") String date);

	@Query(value = "SELECT * FROM ticket WHERE resolved_on=:date", nativeQuery = true)
	List<Ticket> findResolvedDate(@Param("date") String date);

	@Query(value = "SELECT count(raised_on) FROM Ticket ticket", nativeQuery = true)
	Long findCount(@Param("ticket") Ticket ticket);

	@Query(value = "SELECT count(resolved_on) FROM Ticket ticket WHERE ticket.resolved=1", nativeQuery = true)
	Long findCountResolved(@Param("ticket") Ticket ticket);

	@Query(value = "SELECT * FROM ticket WHERE month(raised_on)=:month", nativeQuery = true)
	List<Ticket> findMonthlyReport(@Param("month") int month);

	@Query(value = "SELECT ticket.resolved_by_id FROM Ticket ticket WHERE ticket.resolved=0 GROUP BY ticket.resolved_by_id ORDER BY count(resolved) limit 1", nativeQuery = true)
	int min();

}
