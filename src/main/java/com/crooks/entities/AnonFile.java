/*
 * Copyright (c) 2016.
 */

package com.crooks.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

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

    public AnonFile(int id, String originalFileName, String realFileName) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.realFileName = realFileName;
    }


    public AnonFile(String originalFileName, String realFileName) {
        this.originalFileName = originalFileName;
        this.realFileName = realFileName;
    }

    public AnonFile() {
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
}
