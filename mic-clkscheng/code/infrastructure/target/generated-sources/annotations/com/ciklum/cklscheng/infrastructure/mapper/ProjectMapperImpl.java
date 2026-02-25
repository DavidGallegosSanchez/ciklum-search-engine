package com.ciklum.cklscheng.infrastructure.mapper;

import com.ciklum.cklscheng.domain.entity.Project;
import com.ciklum.cklscheng.infrastructure.entity.BaseProject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-25T10:49:03+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public Project toProject(BaseProject baseProject) {
        if ( baseProject == null ) {
            return null;
        }

        Project project = new Project();

        project.setName( baseProject.getName() );
        project.setDescription( baseProject.getDescription() );
        List<String> list = baseProject.getTags();
        if ( list != null ) {
            project.setTags( new ArrayList<String>( list ) );
        }
        project.setCreatedAt( baseProject.getCreatedAt() );

        return project;
    }

    @Override
    public List<Project> toProject(List<BaseProject> baseProject) {
        if ( baseProject == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( baseProject.size() );
        for ( BaseProject baseProject1 : baseProject ) {
            list.add( toProject( baseProject1 ) );
        }

        return list;
    }
}
