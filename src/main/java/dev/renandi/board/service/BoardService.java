package dev.renandi.board.service;

import dev.renandi.board.dto.board.BoardRequestDto;
import dev.renandi.board.dto.board.BoardResponseDto;
import dev.renandi.board.entity.Board;

import java.util.List;

public interface BoardService {

    BoardResponseDto create(BoardRequestDto dto);

    List<BoardResponseDto> findAll();

    BoardResponseDto findById(Long id);

    Board findEntityById(Long id);

    BoardResponseDto update (Long boardId, BoardRequestDto dto);

    boolean delete (Long id);
}
