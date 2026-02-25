package com.ciklum.cklscheng.domain.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder(toBuilder = true)
@Getter
@ToString(callSuper = true)
public class BaseProjectQuerySearch {

    protected String name;

    protected List<String> tags;

    @Builder.Default
    protected QueryPagination pagination = QueryPagination.of();
}
