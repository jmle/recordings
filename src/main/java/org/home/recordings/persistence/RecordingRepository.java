package org.home.recordings.persistence;

import org.home.recordings.domain.Recording;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recordings", path = "recordings")
public interface RecordingRepository extends PagingAndSortingRepository<Recording, String>,
        QuerydslPredicateExecutor<Recording> {

}
