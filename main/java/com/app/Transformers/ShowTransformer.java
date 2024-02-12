package com.app.Transformers;

import com.app.RequestDtos.ShowEntryDto;
import com.app.models.Show;


public class ShowTransformer {

    public static Show showDtoToShow(ShowEntryDto showEntryDto) {
        Show show = Show.builder()
                .time(showEntryDto.getShowStartTime())
                .date(showEntryDto.getShowDate())
                .build();

        return show;
    }
}
