package dev.renandi.board.mapper;

import dev.renandi.board.dto.BoardColumnResponseDto;
import dev.renandi.board.dto.board.BoardRequestDto;
import dev.renandi.board.dto.board.BoardResponseDto;
import dev.renandi.board.entity.Board;
import dev.renandi.board.entity.BoardColumn;

public class BoardMapper {

    public static Board toEntity(BoardRequestDto dto) {
        return new Board(dto.name(), dto.additionalBoardColumnList());
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getName(),
                board.getColumns().stream().map(BoardMapper::toColumnDto).toList()
        );
    }

    public static BoardColumnResponseDto toColumnDto (BoardColumn column) {
        return new BoardColumnResponseDto(
                column.getId(),
                column.getName(),
                column.getKind(),
                column.getColumnOrder(),
                column.getCardList().stream().map(CardMapper::toDto).toList()
        );
    }
}
