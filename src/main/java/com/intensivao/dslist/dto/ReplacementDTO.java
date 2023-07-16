package com.intensivao.dslist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReplacementDTO {
    private Integer sourceIndex;
    private Integer destinationIndex;
}
