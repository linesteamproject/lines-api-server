package com.linesteams.linesapiserver.lines.service;

import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.LinesShareLog;
import com.linesteams.linesapiserver.lines.repository.ShareLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogService {

    private final ShareLogRepository shareLogRepository;

    public LogService(ShareLogRepository shareLogRepository) {
        this.shareLogRepository = shareLogRepository;
    }

    public void createShareLog(Lines lines) {
        shareLogRepository.save(new LinesShareLog(lines.getId(), lines.getRatio(), lines.getBackground()));
    }
}
