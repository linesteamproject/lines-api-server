package com.linesteams.linesapiserver.lines.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "lines_share_log")
@EntityListeners(AuditingEntityListener.class)
public class LinesShareLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "int")
    private Long linesId;

    @Column(columnDefinition = "varchar(16)")
    @Enumerated(EnumType.STRING)
    private Ratio ratio;

    @Column(columnDefinition = "varchar(16)")
    private String background;

    @CreatedDate
    private LocalDateTime createdDateTime;

    public LinesShareLog() {
    }

    public LinesShareLog(Long linesId, Ratio ratio, String background) {
        this.linesId = linesId;
        this.ratio = ratio;
        this.background = background;
    }

    public Long getId() {
        return id;
    }

    public Long getLinesId() {
        return linesId;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public String getBackground() {
        return background;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }
}
