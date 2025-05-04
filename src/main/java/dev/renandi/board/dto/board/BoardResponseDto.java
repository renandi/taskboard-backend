package dev.renandi.board.dto.board;

import dev.renandi.board.dto.BoardColumnResponseDto;
import dev.renandi.board.entity.BoardColumn;

import java.util.List;

public record BoardResponseDto (
        Long id,
        String name,
        List<BoardColumnResponseDto> boardColumnList
) {
}
