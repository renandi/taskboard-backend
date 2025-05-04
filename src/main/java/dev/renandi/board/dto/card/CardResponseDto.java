package dev.renandi.board.dto.card;

import dev.renandi.board.dto.block.BlockResponseDto;
import dev.renandi.board.entity.Block;
import dev.renandi.board.entity.BoardColumn;

import java.time.OffsetDateTime;
import java.util.List;

public record CardResponseDto(
        Long id,
        Long boardColumnId,
        String title,
        String description,
        OffsetDateTime createdAt,
        List<BlockResponseDto> blocks
) {
}
