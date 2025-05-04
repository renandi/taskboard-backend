package dev.renandi.board.dto.card;

import dev.renandi.board.entity.Block;
import dev.renandi.board.entity.BoardColumn;

import java.time.OffsetDateTime;
import java.util.List;

public record CardRequestDto(
        String title,
        String description
) {
}
