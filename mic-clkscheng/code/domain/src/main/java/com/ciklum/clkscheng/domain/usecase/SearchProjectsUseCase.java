package com.ciklum.clkscheng.domain.usecase;

import com.ciklum.clkscheng.domain.entity.Project;
import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;

import java.util.List;

public interface SearchProjectsUseCase {

    List<Project> searchProducts(final BaseProjectQuerySearch querySearch);
}
