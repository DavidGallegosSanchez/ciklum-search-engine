package com.ciklum.cklscheng.infrastructure.builder;

import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;
import org.bson.BsonDocument;

import java.util.function.Predicate;
import java.util.function.Supplier;

public record SortCriteria<Q extends BaseProjectQuerySearch>(
        Predicate<Q> predicate,
        Supplier<BsonDocument> sortFunction) {

    public boolean shouldSupportSortFor(final Q querySearch) {
        return this.predicate.test(querySearch);
    }

    public BsonDocument getSortOperator() {
        return this.sortFunction().get();
    }
}
