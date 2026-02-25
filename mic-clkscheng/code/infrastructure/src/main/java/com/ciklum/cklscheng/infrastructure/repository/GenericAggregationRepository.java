package com.ciklum.cklscheng.infrastructure.repository;

import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.infrastructure.entity.BaseProject;
import com.ciklum.cklscheng.infrastructure.mapper.ProjectMapper;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.List;

public interface GenericAggregationRepository<T extends BaseProject>{

    default List<Project> mapDocumentsToProjects(
            final ProjectMapper projectMapper,
            final AggregationResults<T> aggResults,
            final boolean isBackwards) {
        final List<Project> products = aggResults.getMappedResults()
                .stream()
                .map(projectMapper::toProject)
                .toList();
        return isBackwards ? products.reversed() : products;
    }
}
