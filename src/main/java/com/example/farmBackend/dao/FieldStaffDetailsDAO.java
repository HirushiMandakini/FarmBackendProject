package com.example.farmBackend.dao;

import com.example.farmBackend.entity.impl.FieldStaffDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldStaffDetailsDAO extends JpaRepository<FieldStaffDetails, String> {
}
