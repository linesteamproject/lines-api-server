package com.linesteams.linesapiserver.version.controller;

import com.linesteams.linesapiserver.dto.ResponseDto;
import com.linesteams.linesapiserver.version.service.VersionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    private final VersionService versionService;

    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @PutMapping("/v1/versions")
    public ResponseDto<String> putVersions(@RequestParam("version") String version) {
        versionService.putVersion(version);
        return ResponseDto.of(version);
    }

    @GetMapping("/v1/versions/check-critical-update-version")
    public ResponseDto<Void> checkCriticalUpdateVersion(@RequestParam("version") String version) {
        versionService.checkCriticalUpdateVersion(version);
        return ResponseDto.of(null);
    }
}
