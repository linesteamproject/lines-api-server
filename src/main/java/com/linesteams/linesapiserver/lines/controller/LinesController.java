package com.linesteams.linesapiserver.lines.controller;

import com.linesteams.linesapiserver.dto.ResponseDto;
import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.service.LinesService;
import com.linesteams.linesapiserver.resolver.MemberId;
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
    public ResponseDto<LinesResponse> createLines(@RequestBody LinesRequest request,
                                                  @MemberId Long memberId) {
        request.setMemberId(memberId);
        return ResponseDto.of(linesService.createLines(request));
    }

    @GetMapping("/v1/lines")
    public ResponseDto<Page<LinesResponse>> getLinesList(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size,
                                                         @MemberId Long memberId) {
        return ResponseDto.of(linesService.getLinesList(memberId, PageRequest.of(page, size)));
    }
}
