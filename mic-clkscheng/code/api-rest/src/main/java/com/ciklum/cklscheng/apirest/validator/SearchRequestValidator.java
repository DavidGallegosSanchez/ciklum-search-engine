package com.ciklum.cklscheng.apirest.validator;

import com.ciklum.cklscheng.apirest.entity.dto.ProjectSearchRequestDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface SearchRequestValidator<T> extends Validator {

    @Override
    default boolean supports(Class<?> clazz) {
        return ProjectSearchRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    default void validate(Object target, Errors errors) {
        // No errors object handling
    }

    void validateRequest(T searchRequest);
}
