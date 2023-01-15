package com.linesteams.linesapiserver.lines.repository;

import com.linesteams.linesapiserver.lines.domain.Lines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinesRepository extends JpaRepository<Lines, Long> {

    Page<Lines> findAllByMemberIdAndDeleted(Long memberId, Pageable pageable, boolean deleted);

    Optional<Lines> findByIdAndDeleted(Long id, boolean deleted);

    Optional<Lines> findByIdAndMemberIdAndDeleted(Long id, Long memberId, boolean deleted);
}
