package com.ciklum.cklscheng.infrastructure.mapper;

import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.infrastructure.entity.BaseProject;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface ProjectMapper {

    Project toProject(BaseProject baseProject);

    List<Project> toProject(List<BaseProject> baseProject);
}
