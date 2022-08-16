package com.linesteams.linesapiserver.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookInfoDto {
    private List<BookInfoItemDto> items;

    public BookInfoDto(List<BookInfoItemDto> items) {
        this.items = items;
    }
}
