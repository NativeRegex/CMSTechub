package com.cmstechub.resume.repository;

import com.cmstechub.resume.entity.ResumeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeProfileRepository extends JpaRepository<ResumeProfile, Long> {
}
