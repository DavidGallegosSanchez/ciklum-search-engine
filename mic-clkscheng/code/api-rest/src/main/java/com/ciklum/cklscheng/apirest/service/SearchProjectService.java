package com.ciklum.cklscheng.apirest.service;

import com.ciklum.cklscheng.apirest.controller.AbstractSearchController;
import com.ciklum.cklscheng.apirest.entity.dto.ProjectSearchRequestDTO;
import com.ciklum.cklscheng.apirest.entity.dto.ProjectSearchResponseDTO;
import com.ciklum.cklscheng.apirest.entity.dto.ProjectSearchResponseWrapperDTO;
import com.ciklum.cklscheng.apirest.validator.SearchRequestValidator;
import com.ciklum.clkscheng.domain.entity.Project;
import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;
import com.ciklum.clkscheng.domain.usecase.SearchProjectsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SearchProjectService extends AbstractSearchController<ProjectSearchRequestDTO> {

    private final SearchProjectsUseCase searchProjectsUseCase;

    @Autowired
    public SearchProjectService(List<SearchRequestValidator<ProjectSearchRequestDTO>> validators,
                                SearchProjectsUseCase searchProjectsUseCase) {
        super(validators);
        this.searchProjectsUseCase = searchProjectsUseCase;
    }

    public ResponseEntity<ProjectSearchResponseWrapperDTO> searchProjects(
            final ProjectSearchRequestDTO projectSearchRequestDTO) {
        log.info("Handling request:: {}", projectSearchRequestDTO);

        this.validate(projectSearchRequestDTO);

        final var querySearch = this.buildQuerySearch(projectSearchRequestDTO);
        final var allProducts = this.searchProjectsUseCase.searchProducts(querySearch);

        return ResponseEntity.ok(this.buildResponse(allProducts));
    }

    private ProjectSearchResponseWrapperDTO buildResponse(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) {
            return new ProjectSearchResponseWrapperDTO(List.of());
        }

        List<ProjectSearchResponseDTO> dtoList = projects.stream()
                .map(project -> {
                    ProjectSearchResponseDTO dto = new ProjectSearchResponseDTO();
                    dto.setName(project.getName());
                    dto.setDescription(project.getDescription());
                    dto.setTags(project.getTags());
                    dto.setCreatedAt(project.getCreatedAt());
                    return dto;
                })
                .toList();

        return new ProjectSearchResponseWrapperDTO(dtoList);
    }

    @Override
    protected BaseProjectQuerySearch buildQuerySearch(final ProjectSearchRequestDTO searchRequestDTO) {
        return BaseProjectQuerySearch.builder()
                .name(searchRequestDTO.getName())
                .tags(searchRequestDTO.getTags())
                .build();
    }

}
