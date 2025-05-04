package dev.renandi.board.dto.block;

import java.time.OffsetDateTime;

public record BlockRequestDto (
        String blockCause,
        OffsetDateTime blockIn,
        String unblockCause,
        OffsetDateTime unblockIn
) {
}
