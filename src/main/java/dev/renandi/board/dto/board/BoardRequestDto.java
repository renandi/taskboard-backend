package dev.renandi.board.dto.board;

import dev.renandi.board.entity.BoardColumn;

import java.util.List;

public record BoardRequestDto(
        String name,
        List<BoardColumn> additionalBoardColumnList
) {
}
