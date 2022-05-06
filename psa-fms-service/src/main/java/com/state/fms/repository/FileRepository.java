package com.state.fms.repository;

import com.state.fms.model.File;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    @Query("{_id: { $in: ?0 } })")
    List<File> findByIds(List<String> ids);
}
