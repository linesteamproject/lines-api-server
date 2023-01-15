package com.linesteams.linesapiserver.lines.controller;

import com.linesteams.linesapiserver.dto.ResponseDto;
import com.linesteams.linesapiserver.lines.dto.LinesCreateRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.dto.LinesUpdateRequest;
import com.linesteams.linesapiserver.lines.service.LinesService;
import com.linesteams.linesapiserver.resolver.MemberId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseDto<LinesResponse> createLines(@RequestBody LinesCreateRequest request,
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

    @PutMapping("/v1/lines/{id}")
    public ResponseDto<LinesResponse> updateLines(@RequestBody LinesUpdateRequest request,
                                                  @MemberId Long memberId,
                                                  @PathVariable Long id) {

        request.setMemberId(memberId);
        request.setId(id);
        return ResponseDto.of(linesService.updateLines(request));
    }

    @DeleteMapping("/v1/lines/{id}")
    public ResponseDto<Void> deleteLines(@MemberId Long memberId,
                                         @PathVariable Long id) {

        linesService.deleteLines(memberId, id);
        return ResponseDto.of(null);
    }

    @PostMapping("/v1/lines/{id}/share-log")
    public ResponseDto<Void> createShareLog(@PathVariable Long id) {
        linesService.createShareLog(id);

        return ResponseDto.of(null);
    }
}
