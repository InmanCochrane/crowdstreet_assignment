package com.crowdstreet.assignment.data;

import com.crowdstreet.assignment.data.model.DocumentRequest;
import com.crowdstreet.assignment.data.model.ProcessingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DocumentRequestRepository
        extends JpaRepository<DocumentRequest, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(" UPDATE DocumentRequest " +
            "SET    status = ?2,    " +
            "       detail = ?3     " +
            "WHERE  id = ?1         ")
    void updateStatus(long documentRequestId, ProcessingStatus status, String detail);
}
