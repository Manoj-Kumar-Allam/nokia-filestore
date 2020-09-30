package com.nokia.esp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nokia.esp.core.entities.FileStore;

/**
 * 
 * The Filestore Repository
 *
 */
@Repository
public interface FilestoreRepository extends JpaRepository<FileStore, Long>{

}
