package com.linesteams.linesapiserver.lines.controller;

import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.service.LinesService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinesController {

    private final LinesService linesService;

    public LinesController(LinesService linesService) {
        this.linesService = linesService;
    }

    @PostMapping("/v1/lines")
    public LinesResponse createLines(@ModelAttribute LinesRequest request) {
        return linesService.createLines(request);
    }

}
