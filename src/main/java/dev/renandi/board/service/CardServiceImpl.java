package dev.renandi.board.service;

import dev.renandi.board.dto.block.BlockRequestDto;
import dev.renandi.board.dto.board.BoardRequestDto;
import dev.renandi.board.dto.board.BoardResponseDto;
import dev.renandi.board.dto.card.CardRequestDto;
import dev.renandi.board.dto.card.CardResponseDto;
import dev.renandi.board.entity.Block;
import dev.renandi.board.entity.Board;
import dev.renandi.board.entity.BoardColumn;
import dev.renandi.board.entity.Card;
import dev.renandi.board.mapper.CardMapper;
import dev.renandi.board.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final BoardService boardService;

    @Override
    public CardResponseDto create(Long boardId, Integer columnOrder, CardRequestDto dto) {

        Board board = boardService.findEntityById(boardId);

        Card card = CardMapper.toEntity(dto);

        for (BoardColumn boardColumn : board.getColumns()) {
            if (boardColumn.getColumnOrder().equals(columnOrder)) {
                card.setBoardColumn(boardColumn);
                card.setCreatedAt(OffsetDateTime.now());
            }
        }

        card = repository.save(card);

        return CardMapper.toDto(card);
    }

    @Override
    public List<CardResponseDto> findAll() {
        return repository.findAll().stream().map(CardMapper::toDto).toList();
    }

    @Override
    public CardResponseDto findById(Long id) {
        Card card = repository.findById(id).orElseThrow(()-> new NoSuchElementException());
        return CardMapper.toDto(card);
    }

    @Override
    public Card findEntityById(Long id) {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public boolean moveCard (Long boardId, Long cardId, Integer columnOrder) {

        Board board = boardService.findEntityById(boardId);

        Card card = findEntityById(cardId);

        for (BoardColumn boardColumn : board.getColumns()) {
            if (boardColumn.getColumnOrder().equals(columnOrder)) {
                card.setBoardColumn(boardColumn);
                repository.save(card);
                return true;
            }
        }
        return false;
    }

    @Override
    public void blockCard(Long boardId, Long cardId, BlockRequestDto dto) {
        Card card = repository.findById(cardId).orElseThrow(() -> new NoSuchElementException());

        List<Block> cardBlocks = card.getBlocks();
        for (Block block : cardBlocks) {
            if (block.getBlockIn() != null && block.getUnblockIn() == null) {
                System.out.println("Card is already blocked. Unblock it before trying to block again.");
                return;
            }
        }

        Block block = new Block();
        block.setBlockCause(dto.blockCause());
        block.setBlockIn(OffsetDateTime.now());
        block.setCard(card);

        card.getBlocks().add(block);

        repository.save(card);
    }

    @Override
    public void unblockCard(Long boardId, Long cardId, BlockRequestDto dto) {
        Card card = repository.findById(cardId).orElseThrow(() -> new NoSuchElementException());

        List<Block> cardBlocks = card.getBlocks();
        for (Block block : cardBlocks) {
            if (block.getBlockIn() != null && block.getUnblockIn() == null) {
                block.setUnblockIn(OffsetDateTime.now());
                block.setUnblockCause(dto.unblockCause());
                repository.save(card);
                return;
            }
        }
    }

    @Override
    public CardResponseDto update (Long boardId, Long cardId,CardRequestDto dto) {

        Card card = repository.findById(cardId).orElseThrow(NoSuchElementException::new);

        card.setTitle(dto.title());
        card.setDescription(dto.description());

        return CardMapper.toDto(repository.save(card));
    }

    @Override
    public boolean delete (Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card with id " + id + " not found.");
    }
}
