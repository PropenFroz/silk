package com.a03.silk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateEntryGajiGuruRequestDTO extends CreateEntryGajiGuruDetailRequestDTO {
    private long idEntryGajiGuruDetail;
}