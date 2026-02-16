package com.ciklum.clkscheng.domain.filter;

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
 }
