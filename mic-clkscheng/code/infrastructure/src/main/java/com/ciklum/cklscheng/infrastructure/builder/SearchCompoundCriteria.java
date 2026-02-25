package com.ciklum.cklscheng.infrastructure.builder;

import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;

import java.util.function.Function;
import java.util.function.Predicate;

import com.mongodb.client.model.search.SearchOperator;

public record SearchCompoundCriteria<Q extends BaseProjectQuerySearch>(
        Predicate<Q> predicate,
        Function<Q, SearchOperator> searchOperatorFunction,
        Operator operator) {

    public boolean shouldIncludeSearchFilter(final Q querySearch) {
        return this.predicate.test(querySearch);
    }

    public SearchOperator getSearchFilter(final Q querySearch) {
        return this.searchOperatorFunction.apply(querySearch);
    }
}
