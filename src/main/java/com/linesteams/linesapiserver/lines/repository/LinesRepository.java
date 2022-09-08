package com.linesteams.linesapiserver.lines.repository;

import com.linesteams.linesapiserver.lines.domain.Lines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinesRepository extends JpaRepository<Lines, Long> {

    public Page<Lines> findAllByMemberId(Long memberId, Pageable pageable);
}
