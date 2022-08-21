package com.linesteams.linesapiserver.lines.controller;

import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.service.LinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinesController {

    private final LinesService linesService;

    public LinesController(LinesService linesService) {
        this.linesService = linesService;
    }

    @PostMapping("/v1/lines")
    public LinesResponse createLines(@RequestBody LinesRequest request) {
        return linesService.createLines(request);
    }

    @GetMapping("/v1/lines")
    public Page<LinesResponse> getLinesList(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size) {
        return linesService.getLinesList(PageRequest.of(page, size));
    }

}
