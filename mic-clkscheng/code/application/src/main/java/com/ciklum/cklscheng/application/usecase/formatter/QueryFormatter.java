package com.ciklum.cklscheng.application.usecase.formatter;


import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;

/**
 * Interface for query formatters following the Chain of Responsibility pattern. Each formatter transforms a
 * BaseProductQuerySearch based on specific query format rules.
 */
public interface QueryFormatter {

    /**
     * Formats the query search by transforming it according to the specific format rules. If this formatter cannot
     * handle the query, it delegates to the next formatter in the chain.
     *
     * @param querySearch the original query search to format
     * @param <T> the type of query search, must extend BaseProjectQuerySearch
     * @return the formatted query search
     */
    <T extends BaseProjectQuerySearch> T format(T querySearch);
}
