/*
 * Copyright (c) 2016.
 */

package com.crooks.controllers;

import com.crooks.entities.AnonFile;
import com.crooks.services.AnonFileRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by johncrooks on 6/27/16.
 */
@Controller
public class AnonFileController {
    @Autowired
    AnonFileRepository anonFileRepo;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    @RequestMapping(path="/upload", method = RequestMethod.POST)        //File is going to be uploaded as a multipart-file
    public String upload(MultipartFile file) throws IOException {
        File dir = new File("public/files");
        dir.mkdirs();    //creates the directory if it doesn't already exist as stated above

        File  uploadedFile = File.createTempFile("file", file.getOriginalFilename(),dir);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(file.getOriginalFilename(),uploadedFile.getName());
        anonFileRepo.save(anonFile);

        return "redirect:/";

    }



}  // End AnonFileController class
