package com.app.services;

import com.app.Exceptions.MovieDoesNotExists;
import com.app.Exceptions.ShowDoesNotExists;
import com.app.Exceptions.TheaterDoesNotExists;
import com.app.Repositories.MovieRepository;
import com.app.Repositories.ShowRepository;
import com.app.Repositories.TheaterRepository;
import com.app.RequestDtos.ShowEntryDto;
import com.app.RequestDtos.ShowSeatEntryDto;
import com.app.RequestDtos.ShowTimingsDto;
import com.app.Transformers.ShowTransformer;
import com.app.models.Movie;
import com.app.models.Show;
import com.app.models.ShowSeat;
import com.app.models.Theater;
import com.app.models.TheaterSeat;
import com.app.types.SeatType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@CrossOrigin
@Transactional
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(ShowEntryDto showEntryDto) throws MovieDoesNotExists, TheaterDoesNotExists{
    	
    	
        Show show = ShowTransformer.showDtoToShow(showEntryDto);
//        Optional<Movie> movieOpt = movieRepository.findById(showEntryDto.getMovieId());
        Movie movie=movieRepository.getMovieDetailsById(showEntryDto.getMovieId());
        
        Theater theater=theaterRepository.getTheaterDetailsForShow(showEntryDto.getTheaterId());
        if(movie==null) {
            throw new MovieDoesNotExists();
        }
        
//        Optional<Theater> theaterOpt = theaterRepository.findById(showEntryDto.getTheaterId());
        if(theater==null) {
            throw new TheaterDoesNotExists();
        }

//        Theater theater = theaterOpt.get();
//        Movie movie = movieOpt.get();
        
        
        
        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);
         
        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);
        
        return "Show has been added Successfully";
    }

    public String associateShowSeats(ShowSeatEntryDto showSeatEntryDto) throws ShowDoesNotExists{
        Optional<Show> showOpt = showRepository.findById(showSeatEntryDto.getShowId());
        if(showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }
        
        Show show = showOpt.get();
        Theater theater = theaterRepository.getTheaterAndTheaterSeatsById(show.getTheater().getId());
                
 
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeat> showSeatList=show.getShowSeatList();

        for(TheaterSeat theaterSeat : theaterSeatList) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC)) {
                showSeat.setPrice((showSeatEntryDto.getPriceOfClassicSeat()));
            } else {
                showSeat.setPrice(showSeatEntryDto.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }
        show.setShowSeatList(showSeatList);//check
        showRepository.save(show);

        return "Show seats have been associated successfully";
    }

    public List<Time> showTimingsOnDate(ShowTimingsDto showTimingsDto) {
//    	System.out.println(showTimingsDto.toString());
        LocalDate date = showTimingsDto.getShowDate();
        Integer theaterId = showTimingsDto.getTheaterId();
        Integer movieId = showTimingsDto.getMovieId();
       System.out.println(date+" "+theaterId+" "+movieId);
       List<Time> timeList= showRepository.getShowTimingsOnDate(date, theaterId, movieId);

        return timeList;
    }
    
    public String movieHavingMostShows() {
        Integer movieId = showRepository.getMostShowsMovie();
        return movieRepository.findById(movieId).get().getMovieName();
    }
}
