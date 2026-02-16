package com.ciklum.cklscheng.apirest.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectSearchRequestDTO {

    private String name;

    private List<String> tags;
}
