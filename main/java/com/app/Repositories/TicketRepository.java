package com.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
