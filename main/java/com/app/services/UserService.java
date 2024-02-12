package com.app.services;

import com.app.Exceptions.UserAlreadyExistsWithEmail;
import com.app.Exceptions.UserDoesNotExists;
import com.app.Repositories.UserRepository;
import com.app.RequestDtos.UserEntryDto;
import com.app.ResponseDtos.TicketResponseDto;
import com.app.Transformers.TicketTransformer;
import com.app.Transformers.UserTransformer;
import com.app.models.Ticket;
import com.app.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws UserAlreadyExistsWithEmail{
        if(userRepository.findByEmailId(userEntryDto.getEmailId()) != null) {
            throw new UserAlreadyExistsWithEmail();
        }
        User user = UserTransformer.userDtoToUser(userEntryDto);

        userRepository.save(user);
        return "User Saved Successfully";
    }

    public List<TicketResponseDto> allTickets(Integer userId) throws UserDoesNotExists{
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserDoesNotExists();
        }
        User user = userOpt.get();
        List<Ticket> ticketList = user.getTicketList();
//        System.out.println(ticketList.get(1).getTicketId());
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            TicketResponseDto ticketResponseDto = TicketTransformer.returnTicket(ticket.getShow(), ticket);
            ticketResponseDtoList.add(ticketResponseDto);
        }
        System.out.println(ticketResponseDtoList.toString());
        return ticketResponseDtoList;
    }

	public void deleteUser(Integer userId) {
		Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new UserDoesNotExists();
        }
		userRepository.deleteById(userId);
	}
}
