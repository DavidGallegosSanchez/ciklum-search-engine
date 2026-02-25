package com.ciklum.cklscheng.infrastructure.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BaseProject {

    @MongoId
    private String name;

    private String description;

    private List<String> tags;

    private Date createdAt;
}
