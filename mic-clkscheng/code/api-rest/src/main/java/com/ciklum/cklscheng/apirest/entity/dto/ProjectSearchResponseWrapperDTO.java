package com.ciklum.cklscheng.apirest.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectSearchResponseWrapperDTO {

    private List<ProjectSearchResponseDTO> projects;
}
