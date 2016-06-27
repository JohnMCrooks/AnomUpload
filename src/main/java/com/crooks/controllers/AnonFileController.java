/*
 * Copyright (c) 2016.
 */

package com.crooks.controllers;

import com.crooks.entities.AnonFile;
import com.crooks.services.AnonFileRepository;
import com.crooks.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public String upload(MultipartFile file, Boolean isPerm, String description, String password) throws IOException, PasswordStorage.CannotPerformOperationException {
        File dir = new File("public/files");
        dir.mkdirs();    //creates the directory if it doesn't already exist as stated above
        int nonPerms = anonFileRepo.countByIsPermFalse();

        if( nonPerms <4) {
            File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(file.getBytes());

            if (description==null){
                description = "Unknown File";
            }
            AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), isPerm, description, PasswordStorage.createHash(password));
            anonFileRepo.save(anonFile);
        } else if (nonPerms==4){

        int smallID = anonFileRepo.selectSmallestId();
            anonFileRepo.delete(smallID);
            File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(file.getBytes());

            if (description==null){
                description = "Unknown File";
            }
            AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), isPerm, description, PasswordStorage.createHash(password));
            anonFileRepo.save(anonFile);
        }
        return "redirect:/";

    } // End upload Method

    @RequestMapping(path="/delete", method = RequestMethod.POST)
    public String deleteFile(int id, String password) throws Exception {
        AnonFile fileToDelete = (AnonFile) anonFileRepo.findOne(id);
        if (PasswordStorage.verifyPassword(password, fileToDelete.getPassword())){
            anonFileRepo.delete(id);
        }else{
            throw new Exception("Wrong Password Man, Move Along");
        }


        return "redirect:/";
    }


}  // End AnonFileController class
