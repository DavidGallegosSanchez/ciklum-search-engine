package com.ciklum.cklscheng.domain.repository;

import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;

import java.util.List;

public interface ProjectSearchRepository {

    List<Project> findAllByQuerySearch(final BaseProjectQuerySearch querySearch);
}
