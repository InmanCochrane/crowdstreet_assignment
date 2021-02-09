package com.crowdstreet.assignment.data;

import com.crowdstreet.assignment.data.model.DocumentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentRequestRepository
        extends JpaRepository<DocumentRequest, Long> {
}
