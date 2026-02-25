package com.ciklum.cklscheng.infrastructure.repository;

import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.domain.filter.BaseProjectQuerySearch;
import com.ciklum.cklscheng.domain.filter.QueryPagination;
import com.ciklum.cklscheng.domain.repository.ProjectSearchRepository;
import com.ciklum.cklscheng.infrastructure.entity.BaseProject;
import com.ciklum.cklscheng.infrastructure.mapper.ProjectMapper;
import com.ciklum.cklscheng.infrastructure.service.CompoundSearchService;
import com.mongodb.client.model.search.SearchOperator;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Repository
public class ProjectSearchCompoundRepositoryImpl implements ProjectSearchRepository,
        GenericAggregationRepository<BaseProject> {

    private final MongoTemplate mongoTemplate;

    private final ProjectMapper projectMapper;

    private final CompoundSearchService<BaseProjectQuerySearch> compoundProjectSearchService;

    @Autowired
    public ProjectSearchCompoundRepositoryImpl(final MongoTemplate mongoTemplate,
            final ProjectMapper projectMapper,
            final CompoundSearchService<BaseProjectQuerySearch> compoundProjectSearchService) {
        this.mongoTemplate = mongoTemplate;
        this.projectMapper = projectMapper;
        this.compoundProjectSearchService = compoundProjectSearchService;
    }


    @Override
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

    private Bson buildSearchStage(final BaseProjectQuerySearch querySearch, final SearchOperator compoundSearch) {
        final BsonDocument compoundSearchBson = compoundSearch.toBsonDocument();

        final QueryPagination pagination = querySearch.getPagination();
        if (isNotBlank(pagination.nextCursor())) {
            compoundSearchBson.append("searchAfter", new BsonString(pagination.nextCursor()));
        } else if (isNotBlank(pagination.previousCursor())) {
            compoundSearchBson.append("searchBefore", new BsonString(pagination.previousCursor()));
        }

        final BsonDocument sortOperator = this.compoundProjectSearchService.createSortOperationForQuery();

        return new BsonDocument("$search", compoundSearchBson
                .append("sort", sortOperator)
                .append("index", new BsonString("dyn_idx0")));
    }
}
