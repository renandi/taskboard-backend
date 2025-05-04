package dev.renandi.board.service;

import dev.renandi.board.dto.board.BoardRequestDto;
import dev.renandi.board.dto.board.BoardResponseDto;
import dev.renandi.board.dto.card.CardResponseDto;
import dev.renandi.board.entity.Board;
import dev.renandi.board.entity.BoardColumn;
import dev.renandi.board.entity.Card;
import dev.renandi.board.mapper.BoardMapper;
import dev.renandi.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    /**
     Requires a Dto containing "name" and a list of additional columns "additionalBoardColumnList".

     @param dto Object containing "name" and a list of additional columns called "additionalBoardColumnList";
     Each additional columns object should have "name" and "kind".;
     */
    @Override
    public BoardResponseDto create(BoardRequestDto dto) {

        Board board = repository.save(new Board(dto.name()));

        List<BoardColumn> columns = createColumns(board, dto.additionalBoardColumnList());

        board.getColumns().clear();
        board.getColumns().addAll(columns);

        board = repository.save(board);

        return BoardMapper.toDto(board);
    }

    @Override
    public List<BoardResponseDto> findAll() {
        return repository.findAll().stream().map(BoardMapper::toDto).toList();
    }

    @Override
    public BoardResponseDto findById(Long id) {
        Board board = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return BoardMapper.toDto(board);
    }

    @Override
    public Board findEntityById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public BoardResponseDto update(Long boardId, BoardRequestDto dto) {
        Board board = repository.findById(boardId).orElseThrow(NoSuchElementException::new);

        board.setName(dto.name());

        board.getColumns().clear();
        List<BoardColumn> columns = createColumns(board, dto.additionalBoardColumnList());
        board.getColumns().addAll(columns);

        return BoardMapper.toDto(repository.save(board));
    }

    @Override
    public boolean delete (Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board with id "+id+" not found.");
    }

    public List<BoardColumn> createColumns (Board board, List<BoardColumn> additionalColumns) {

        List<BoardColumn> columns = new ArrayList<>();

        columns.add(new BoardColumn(
                "First column",
                "Initial",
                1,
                board
        ));

        int i = 2;

        for (BoardColumn bc : additionalColumns) {
            bc.setBoard(board);
            bc.setColumnOrder(i++);
            columns.add(bc);
        }

        columns.add(new BoardColumn(
                "Final column",
                "Final",
                i++,
                board
        ));

        columns.add(new BoardColumn(
                "Canceled",
                "Canceled",
                i,
                board
        ));

        return columns;
    }

}
