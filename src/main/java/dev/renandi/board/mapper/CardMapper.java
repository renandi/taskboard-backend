package dev.renandi.board.mapper;

import dev.renandi.board.dto.card.CardRequestDto;
import dev.renandi.board.dto.card.CardResponseDto;
import dev.renandi.board.entity.Card;

public class CardMapper {

    public static Card toEntity(CardRequestDto dto) {
        return new Card(dto.title(), dto.description());
    }

    public static CardResponseDto toDto(Card card) {
        return new CardResponseDto(
                card.getId(),
                card.getBoardColumn().getId(),
                card.getTitle(),
                card.getDescription(),
                card.getCreatedAt(),
                card.getBlocks().stream().map(BlockMapper::toDto).toList()
                );
    }
}
