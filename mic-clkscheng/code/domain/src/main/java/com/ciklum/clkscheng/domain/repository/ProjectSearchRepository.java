package com.ciklum.clkscheng.domain.repository;

import com.ciklum.clkscheng.domain.entity.Project;
import com.ciklum.clkscheng.domain.filter.BaseProjectQuerySearch;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProjectSearchRepository {

    private final MongoTemplate mongoTemplate;


    public ProjectSearchRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Project> findAllByQuerySearch(final BaseProjectQuerySearch querySearch) {
        Query query = new Query();
        if (querySearch.getName() != null) {
            query.addCriteria(Criteria.where("name").regex(querySearch.getName(), "i"));
        }
        if (querySearch.getTags() != null && !querySearch.getTags().isEmpty()) {
            query.addCriteria(Criteria.where("tags").in(querySearch.getTags()));
        }
        return mongoTemplate.find(query, Project.class);
    }
}
