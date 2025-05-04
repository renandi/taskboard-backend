package dev.renandi.board.dto;

import dev.renandi.board.dto.card.CardResponseDto;

import java.util.List;

public record BoardColumnResponseDto(
        Long id,
        String name,
        String kind,
        Integer columnOrder,
        List<CardResponseDto> cards
)
{
}
