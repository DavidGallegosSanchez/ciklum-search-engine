package com.ciklum.cklscheng.domain.usecase;

import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;

import java.util.List;

public interface SearchProjectsUseCase {

    List<Project> searchProducts(final BaseProjectQuerySearch querySearch);
}
