package com.ciklum.cklscheng.domain.filter;

public record QueryPagination(String previousCursor, String nextCursor, Integer limit) {

    public static QueryPagination of() {
        return of(null, null, null);
    }

    public static QueryPagination of(final String previousCursor, final String nextCursor, final Integer limit) {
        return new QueryPagination(previousCursor, nextCursor, limit);
    }

    /**
     * Indicates if the pagination is in reverse order.
     *
     * @return true if the pagination is in reverse order, false otherwise.
     */
    public boolean isBackwards() {
        return previousCursor != null;
    }
}
