package com.linesteams.linesapiserver.version.domain;

import com.linesteams.linesapiserver.config.jpa.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "version")
public class Version extends BaseTimeEntity implements Comparable<String> {
    public static final String VERSION_SPLIT = "\\.";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(16)")
    private String version;

    public Version() {
    }

    public Version(String version) {
        this.version = version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int compareTo(String version) {
        String[] right = this.version.split(VERSION_SPLIT);
        String[] left = version.split(VERSION_SPLIT);

        for (int i = 0; i < 3; i++) {
            int result = compareByDigit(right, left, i);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    private int compareByDigit(String[] right, String[] left, int index) {
        return Integer.parseInt(right[index]) - Integer.parseInt(left[index]);
    }
}
