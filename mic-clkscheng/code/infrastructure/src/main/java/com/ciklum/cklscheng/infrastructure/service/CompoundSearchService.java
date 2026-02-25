package com.ciklum.cklscheng.infrastructure.service;

import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;
import com.ciklum.cklscheng.infrastructure.builder.Operator;
import com.ciklum.cklscheng.infrastructure.builder.SearchCompoundCriteria;
import com.ciklum.cklscheng.infrastructure.builder.SortCriteria;
import com.mongodb.client.model.search.CompoundSearchOperatorBase;
import com.mongodb.client.model.search.SearchOperator;
import lombok.RequiredArgsConstructor;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CompoundSearchService<Q extends BaseProjectQuerySearch> {

    private final List<SearchCompoundCriteria<Q>> searchCompoundCriteriaList;

    private final List<SortCriteria<Q>> sortCriteriaList;

    public CompoundSearchOperatorBase createCompoundSearchForQuery(final Q querySearch) {

        final var groupedFilters = this.searchCompoundCriteriaList.stream()
                .filter(sc -> sc.shouldIncludeSearchFilter(querySearch))
                .collect(groupingBy(SearchCompoundCriteria::operator,
                        mapping(sc -> sc.getSearchFilter(querySearch), toList())));

        var compoundSearch = SearchOperator.compound();
        if (!isEmpty(groupedFilters.get(Operator.MUST))) {
            compoundSearch = compoundSearch.must(groupedFilters.get(Operator.MUST));
        }
        if (!isEmpty(groupedFilters.get(Operator.MUST_NOT))) {
            compoundSearch = compoundSearch.mustNot(groupedFilters.get(Operator.MUST_NOT));
        }
        if (!isEmpty(groupedFilters.get(Operator.SHOULD))) {
            compoundSearch = compoundSearch.should(groupedFilters.get(Operator.SHOULD)).minimumShouldMatch(1);
        }

        return compoundSearch;
    }

    public BsonDocument buildCompoundSearch(Q querySearch) {
        BsonArray mustArray = new BsonArray();
        BsonArray filterArray = new BsonArray();

        // Autocomplete en "name"
        if (querySearch.getName() != null && !querySearch.getName().isBlank()) {
            BsonDocument autocomplete = new BsonDocument()
                    .append("autocomplete", new BsonDocument()
                            .append("query", new BsonString(querySearch.getName()))
                            .append("path", new BsonString("name")));
            mustArray.add(autocomplete);
        }

        // Filter in sobre tags (arrays)
        if (querySearch.getTags() != null && !querySearch.getTags().isEmpty()) {
            BsonArray tagsArray = new BsonArray();
            querySearch.getTags().forEach(t -> tagsArray.add(new BsonString(t)));

            BsonDocument inFilter = new BsonDocument()
                    .append("in", new BsonDocument()
                            .append("path", new BsonString("tags"))
                            .append("value", tagsArray));
            filterArray.add(inFilter);
        }

        // Compound
        BsonDocument compound = new BsonDocument()
                .append("compound", new BsonDocument()
                        .append("must", mustArray)
                        .append("filter", filterArray));

        return compound;
    }


    public BsonDocument createSortOperationForQuery() {
        /*return this.sortCriteriaList.stream()
                .filter(sc -> sc.shouldSupportSortFor(querySearch))
                .map(SortCriteria::getSortOperator)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No sort criteria found for query " + querySearch));*/
        return new BsonDocument("name", new BsonInt32(1));
    }
}
