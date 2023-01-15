package com.linesteams.linesapiserver.lines.dto;

import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesUpdateRequest {
    private Long id;

    private Long memberId;

    private String content;

    private Ratio ratio;
    private String background;

    public LinesUpdateRequest() {
    }

    public LinesUpdateRequest(String content, Ratio ratio, String background) {
        this.content = content;
        this.ratio = ratio;
        this.background = background;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public String getBackground() {
        return background;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
