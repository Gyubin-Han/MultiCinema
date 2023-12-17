package com.mc.multicinema.ticketing.dao;

import java.util.ArrayList;
import com.mc.multicinema.ticketing.dto.TicketingDTO;
import com.mc.multicinema.user.dto.UserDTO;

public interface TicketingSelectDAO {
	
	public ArrayList<TicketingDTO> selectAllMyTickets(UserDTO dto);
	
	public TicketingDTO selectTicket();

}
