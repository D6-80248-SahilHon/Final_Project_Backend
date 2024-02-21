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
import com.app.ResponseDtos.ShowResponseDto;
import com.app.ResponseDtos.ShowSeatResponseDto;
import com.app.Transformers.ShowTransformer;
import com.app.models.Movie;
import com.app.models.Show;
import com.app.models.ShowSeat;
import com.app.models.Theater;
import com.app.models.TheaterSeat;
import com.app.types.SeatType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    
    @Autowired 
    private ModelMapper mapper;


    public ShowSeatEntryDto addShow(ShowEntryDto showEntryDto) throws MovieDoesNotExists, TheaterDoesNotExists{ //laksh
    	System.out.println(showEntryDto.toString());
    	
        Show show = ShowTransformer.showDtoToShow(showEntryDto);
        Movie movie=movieRepository.getMovieDetailsById(showEntryDto.getMovieId());
        Theater theater=theaterRepository.getTheaterDetailsForShow(showEntryDto.getTheaterId());
        if(movie==null) {
            throw new MovieDoesNotExists();
        }
        
        if(theater==null) {
            throw new TheaterDoesNotExists();
        }
        
        show.setMovie(movie);
        show.setTheater(theater);
        show = showRepository.save(show);
        movie.getShows().add(show);
        theater.getShowList().add(show);
        ShowSeatEntryDto showSeatEntryDto=new ShowSeatEntryDto();
        showSeatEntryDto.setShowId(show.getShowId());
        showSeatEntryDto.setPriceOfClassicSeat(showEntryDto.getPriceOfClassicSeat());
        showSeatEntryDto.setPriceOfPremiumSeat(showEntryDto.getPriceOfPremiumSeat());
        movieRepository.save(movie);
        theaterRepository.save(theater);
        
        return showSeatEntryDto;
    }

    public String associateShowSeats(ShowSeatEntryDto showSeatEntryDto) throws ShowDoesNotExists{
        Optional<Show> showOpt = showRepository.findById(showSeatEntryDto.getShowId());
        
        if(showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }
        
        Show show = showOpt.get();
        System.out.println(show);
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
            System.out.println("in associate");
        }
        show.setShowSeatList(showSeatList);//check
       showRepository.save(show);
        
        return "Show successfully added";
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

    public List<ShowResponseDto> getShowByMovieid(Integer id) {   //to be merged
		List<Show> list=showRepository.getAllShowsOfMovie(id);
		List<ShowResponseDto> li=new ArrayList<ShowResponseDto>();
		System.out.println(list.size());
		for(Show ele:list) {
			ShowResponseDto sr=new ShowResponseDto();
			sr.setMovieId(ele.getMovie().getId());
			sr.setShowDate(ele.getDate());
			sr.setShowId(ele.getShowId());
			sr.setShowStartTime(ele.getTime());
			sr.setTheaterId(ele.getTheater().getId());
			li.add(sr);
		};

			System.out.println(li);
		return li;
	}

    public List<ShowSeatResponseDto> getShowSeatList(Integer id){
    	Show show=showRepository.findById(id).get();
    	List<ShowSeat> showSeatList=show.getShowSeatList();
    	List<ShowSeatResponseDto> rlist=new ArrayList<ShowSeatResponseDto>();
    	for(ShowSeat s:showSeatList) {
    		rlist.add(mapper.map(s,ShowSeatResponseDto.class));
    	};
    	for(ShowSeatResponseDto s:rlist)System.out.println(s.getSeatNo());
    	return rlist;
    }
    
    public List<ShowResponseDto> getShowByTheaterid(Integer id) {   //to be merged
		List<Show> list=showRepository.getAllShowsOfTheater(id) ;
		List<ShowResponseDto> li=new ArrayList<ShowResponseDto>();
		System.out.println(list.size());
		for(Show ele:list) {
			ShowResponseDto sr=new ShowResponseDto();
			sr.setMovieId(ele.getMovie().getId());
			sr.setShowDate(ele.getDate());
			sr.setShowId(ele.getShowId());
			sr.setShowStartTime(ele.getTime());
			sr.setTheaterId(ele.getTheater().getId());
			li.add(sr);
		};

			System.out.println(li);
		return li;
	}
    
    public String deleteShow(Integer id) { //Laksh's api
    	Optional<Show> show = showRepository.findById(id);
    	if(show.isEmpty())
    		throw new ShowDoesNotExists();
    	showRepository.deleteById(id);
    	return "Show deleted successfully!!!";
    }
}
