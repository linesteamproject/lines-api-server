package com.linesteams.linesapiserver.lines.dto;

import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesUpdateRequest {
    private Long id;

    private Long memberId;

    private String background;
    private String font;
    private String textAlignment;
    private String content;
    private Ratio ratio;

    public LinesUpdateRequest() {
    }

    public LinesUpdateRequest(String content, Ratio ratio, String background, String font, String textAlignment) {
        this.content = content;
        this.ratio = ratio;
        this.background = background;
        this.font = font;
        this.textAlignment = textAlignment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBackground() {
        return background;
    }

    public String getFont() {
        return font;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public String getContent() {
        return content;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
