package dev.renandi.board.mapper;

import dev.renandi.board.dto.block.BlockRequestDto;
import dev.renandi.board.dto.block.BlockResponseDto;
import dev.renandi.board.entity.Block;

import java.time.OffsetDateTime;

public class BlockMapper {

    public static BlockResponseDto toDto(Block block) {
        return new BlockResponseDto(
                block.getId(),
                block.getBlockCause(),
                block.getBlockIn(),
                block.getUnblockCause(),
                block.getUnblockIn());
    }
}
