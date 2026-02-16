package com.ciklum.cklscheng.application.usecase;

import com.ciklum.cklscheng.application.usecase.formatter.QueryFormatter;
import com.ciklum.clkscheng.domain.entity.Project;
import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;
import com.ciklum.clkscheng.domain.repository.ProjectSearchRepository;
import com.ciklum.clkscheng.domain.usecase.SearchProjectsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchProjectUseCaseImpl implements SearchProjectsUseCase {

    private final ProjectSearchRepository projectSearchRepository;

    private final QueryFormatter queryFormatter;

    @Override
    public List<Project> searchProducts(BaseProjectQuerySearch querySearch) {
        log.info("Searching query:: {}", querySearch);
        BaseProjectQuerySearch formattedQuery = queryFormatter.format(querySearch);
        return this.projectSearchRepository.findAllByQuerySearch(formattedQuery);
    }
}
