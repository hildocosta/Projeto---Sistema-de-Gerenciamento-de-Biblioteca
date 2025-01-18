package com.hildo.costa.library.model.dto;

public record BookDTO(
        String id,
        String title,
        String author,
        String genre,
        double price
) {
}
