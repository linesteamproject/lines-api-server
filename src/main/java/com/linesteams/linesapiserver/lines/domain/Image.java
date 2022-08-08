package com.linesteams.linesapiserver.lines.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image {

    @Column
    private String path;

    public Image(String path) {
        this.path = path;
    }

    public Image() {
    }
}
