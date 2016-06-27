/*
 * Copyright (c) 2016.
 */

package com.crooks.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by johncrooks on 6/27/16.
 */

@Entity
@Table(name="files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String originalFileName;

    @Column(nullable = false)
    String realFileName;

    @Column
    Boolean isPerm = false;

    @NotNull
    String description;

    @Column(nullable=true)
    String password;

    public AnonFile(int id, String originalFileName, String realFileName, Boolean isPerm, String description, String password) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.realFileName = realFileName;
        this.isPerm = isPerm;
        this.description = description;
        this.password = password;
    }

    public AnonFile(String originalFileName, String realFileName, Boolean isPerm, String description, String password) {
        this.originalFileName = originalFileName;
        this.realFileName = realFileName;
        this.isPerm = isPerm;
        this.description = description;
        this.password = password;
    }

    public AnonFile() {
    }

    public Boolean getPerm() {
        return isPerm;
    }

    public void setPerm(Boolean perm) {
        isPerm = perm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
