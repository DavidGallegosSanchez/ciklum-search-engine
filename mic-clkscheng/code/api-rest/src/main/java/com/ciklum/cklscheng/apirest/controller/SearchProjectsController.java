package com.ciklum.cklscheng.apirest.controller;

import com.ciklum.cklscheng.apirest.entity.dto.ProjectSearchRequestDTO;
import com.ciklum.cklscheng.apirest.entity.dto.ProjectSearchResponseWrapperDTO;
import com.ciklum.cklscheng.apirest.service.SearchProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/projects")
@Slf4j
public class SearchProjectsController {

    private final SearchProjectService searchProjectService;

    public SearchProjectsController(SearchProjectService searchProjectService) {
        this.searchProjectService = searchProjectService;
    }

    @PostMapping
    public ResponseEntity<ProjectSearchResponseWrapperDTO> searchProjects(
            @RequestBody ProjectSearchRequestDTO  projectSearchRequestDTO) {
        return searchProjectService.searchProjects(projectSearchRequestDTO);
    }


}
