package dev.renandi.board.controller;

import dev.renandi.board.dto.block.BlockRequestDto;
import dev.renandi.board.dto.board.BoardRequestDto;
import dev.renandi.board.dto.board.BoardResponseDto;
import dev.renandi.board.dto.card.CardRequestDto;
import dev.renandi.board.dto.card.CardResponseDto;
import dev.renandi.board.service.BoardService;
import dev.renandi.board.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("boards")
public class BoardController {

    private final BoardService boardService;
    private final CardService cardService;


    /*
    Boards and columns
     */
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll () {
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> findById (@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.findById(boardId));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> update(@PathVariable Long boardId, @RequestBody BoardRequestDto dto) {
        return ResponseEntity.ok().body(boardService.update(boardId, dto));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard (@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.delete(boardId)? "Board deleted successfully!" : "Could not find board to delete.");
    }

    /*
    Cards
    */

    /**
     Creates a card in the given column.
    * */
    @PostMapping("/{boardId}/cards")
    public ResponseEntity<CardResponseDto> createCard(
            @PathVariable Long boardId,
            @RequestParam Integer columnOrder,
            @RequestBody CardRequestDto dto
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.create(boardId, columnOrder, dto));
    }

    @PatchMapping("/{boardId}/cards/{cardId}/move")
    public void moveCard(
            @PathVariable Long boardId,
            @PathVariable Long cardId,
            @RequestParam Integer columnOrder
    ) {
        cardService.moveCard(boardId, cardId, columnOrder);
    }


    @PatchMapping("/{boardId}/cards/{cardId}/block")
    public void blockCard(
            @PathVariable Long boardId,
            @PathVariable Long cardId,
            @RequestBody BlockRequestDto dto
    ) {
        cardService.blockCard(boardId, cardId, dto);
    }

    @PatchMapping("/{boardId}/cards/{cardId}/unblock")
    public void unblockCard(
            @PathVariable Long boardId,
            @PathVariable Long cardId,
            @RequestBody BlockRequestDto dto
    ) {
        cardService.unblockCard(boardId, cardId, dto);
    }

    @PutMapping("/{boardId}/cards/{cardId}")
    public ResponseEntity<CardResponseDto> updateCard(
            @PathVariable Long boardId,
            @PathVariable Long cardId,
            @RequestBody CardRequestDto dto
    ) {
        return ResponseEntity.ok(cardService.update(boardId, cardId, dto));
    }

    @DeleteMapping("/{boardId}/cards/{cardId}")
    public ResponseEntity<String> deleteCard (@PathVariable Long cardId) {
        return ResponseEntity.ok(cardService.delete(cardId) ? "Card deleted successfully!" : "Could not find board to delete.");
    }

}
