package com.ciklum.cklscheng.apirest.controller;

import com.ciklum.cklscheng.apirest.validator.SearchRequestValidator;
import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Abstract class for search controllers, providing basic common functionality and resources.
 *
 * @param <T> the type of the request.
 */
public abstract class AbstractSearchController<T> {

    protected List<SearchRequestValidator<T>> validators;

    protected AbstractSearchController(List<SearchRequestValidator<T>> validators) {
        this.validators = validators;
    }

    protected void validate(final T searchRequestDTO) {
        validators.forEach(validator -> validator.validateRequest(searchRequestDTO));
    }

    protected abstract BaseProjectQuerySearch buildQuerySearch(final T searchRequestDTO);

}
