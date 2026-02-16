package com.ciklum.cklscheng.apirest.entity.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectSearchResponseDTO {

    private String name;

    private String description;

    private List<String> tags;

    private Date createdAt;
}
