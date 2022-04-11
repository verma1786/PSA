package com.state.fms.repository;

import com.state.fms.model.LoadFile;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<LoadFile, String> {
    @Query("{_id: { $in: ?0 } })")
    List<LoadFile> findByIds(List<String> ids);
}
