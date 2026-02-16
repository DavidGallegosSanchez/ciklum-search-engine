package com.ciklum.cklscheng.application.usecase.formatter;

import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;
import org.springframework.stereotype.Component;

@Component
public class DefaultQueryFormatter implements QueryFormatter {

    @Override
    public <T extends BaseProjectQuerySearch> T format(T querySearch) {

        return querySearch;
    }
}