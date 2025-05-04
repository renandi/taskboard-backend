package dev.renandi.board.service;

import dev.renandi.board.dto.block.BlockRequestDto;
import dev.renandi.board.dto.board.BoardRequestDto;
import dev.renandi.board.dto.card.CardRequestDto;
import dev.renandi.board.dto.card.CardResponseDto;
import dev.renandi.board.entity.Card;

import java.util.List;

public interface CardService {

//    CardResponseDto create(CardRequestDto dto);

    CardResponseDto create(Long boardId, Integer columnId, CardRequestDto dto);

    List<CardResponseDto> findAll();

    CardResponseDto findById(Long id);

    Card findEntityById(Long id);

    boolean moveCard(Long boardId, Long cardId, Integer columnOrder);

    void blockCard(Long boardId, Long cardId, BlockRequestDto dto);

    void unblockCard(Long boardId, Long cardId, BlockRequestDto dto);

    CardResponseDto update (Long boardId, Long cardId, CardRequestDto dto);

    boolean delete (Long id);

}
