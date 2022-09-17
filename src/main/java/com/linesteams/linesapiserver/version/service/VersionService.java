package com.linesteams.linesapiserver.version.service;

import com.linesteams.linesapiserver.version.domain.Version;
import com.linesteams.linesapiserver.version.repository.VersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    @Transactional(readOnly = true)
    public void checkCriticalUpdateVersion(String version) {
        getVersion().ifPresent(serverVersion -> {
            if (serverVersion.compareTo(version) > 0) {
                throw new RuntimeException("버전 업데이트를 해야합니다.");
            }
        });
    }

    private Optional<Version> getVersion() {
        List<Version> versions = versionRepository.findAll();
        if (versions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(versions.get(0));
    }

    public void putVersion(String version) {
        Version savedVersion = getVersion().orElse(new Version());
        savedVersion.setVersion(version);
        versionRepository.save(savedVersion);
    }
}
