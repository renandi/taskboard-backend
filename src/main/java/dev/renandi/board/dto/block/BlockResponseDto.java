package dev.renandi.board.dto.block;

import java.time.OffsetDateTime;

public record BlockResponseDto(
        Long id,
        String blockCause,
        OffsetDateTime blockIn,
        String unblockCause,
        OffsetDateTime unblockIn) {
}
