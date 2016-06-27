/*
 * Copyright (c) 2016.
 */

package com.crooks.controllers;

import com.crooks.entities.AnonFile;
import com.crooks.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by johncrooks on 6/27/16.
 */
@RestController
public class AnonFIleRestController {
    @Autowired
    AnonFileRepository anonFileRepo;

    @RequestMapping(path="/files", method = RequestMethod.GET)
    public Iterable<AnonFile> getFiles(){
        return anonFileRepo.findAll();
    }

}
