package com.linesteams.linesapiserver.lines.repository;

import com.linesteams.linesapiserver.lines.domain.LinesShareLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareLogRepository extends JpaRepository<LinesShareLog, Long> {
}
