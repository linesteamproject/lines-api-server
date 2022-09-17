package com.linesteams.linesapiserver.version.repository;

import com.linesteams.linesapiserver.version.domain.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version, Long> {
}
