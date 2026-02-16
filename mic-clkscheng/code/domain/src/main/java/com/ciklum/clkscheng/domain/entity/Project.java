package com.ciklum.clkscheng.domain.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Project {

    private String name;

    private String description;

    private List<String> tags;

    private Date createdAt;
}
