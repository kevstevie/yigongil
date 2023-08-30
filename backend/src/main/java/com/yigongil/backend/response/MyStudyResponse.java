package com.yigongil.backend.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

public record MyStudyResponse(
        @Schema(example = "3")
        Long id,
        @Schema(example = "1")
        Integer processingStatus,
        @Schema(example = "1")
        Integer role,
        @Schema(example = "코틀린으로 스프링하기 배울까요")
        String name,
        @Schema(example = "1")
        Integer averageTier,
        @Schema(example = "2023.08.30")
        LocalDate startAt,
        @Schema(example = "5")
        Integer totalRoundCount,
        @Schema(example = "1d")
        String periodOfRound,
        @Schema(example = "3")
        Integer numberOfCurrentMembers,
        @Schema(example = "5")
        Integer numberOfMaximumMembers
) {

}