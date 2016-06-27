/*
 * Copyright (c) 2016.
 */

package com.crooks.services;

import com.crooks.entities.AnonFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 6/27/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    @Query("SELECT MIN(id) FROM AnonFile a WHERE a.isPerm=false")
    public int selectSmallestId();

    public int countByIsPermFalse();
}
