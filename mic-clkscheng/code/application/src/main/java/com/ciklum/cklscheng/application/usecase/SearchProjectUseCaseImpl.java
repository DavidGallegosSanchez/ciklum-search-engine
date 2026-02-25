package com.ciklum.cklscheng.application.usecase;

import com.ciklum.cklscheng.application.usecase.formatter.QueryFormatter;
import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;
import com.ciklum.cklscheng.domain.repository.ProjectSearchRepository;
import com.ciklum.cklscheng.domain.usecase.SearchProjectsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SearchProjectUseCaseImpl implements SearchProjectsUseCase {

    private final ProjectSearchRepository projectSearchRepository;

    private final QueryFormatter queryFormatter;

    public SearchProjectUseCaseImpl(ProjectSearchRepository projectSearchRepository,
                                    QueryFormatter queryFormatter) {
        this.projectSearchRepository = projectSearchRepository;
        this.queryFormatter = queryFormatter;
    }

    @Override
    public List<Project> searchProducts(BaseProjectQuerySearch querySearch) {
        log.info("Searching query:: {}", querySearch);
        BaseProjectQuerySearch formattedQuery = queryFormatter.format(querySearch);
        return this.projectSearchRepository.findAllByQuerySearch(formattedQuery);
    }
}
