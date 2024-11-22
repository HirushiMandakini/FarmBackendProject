package com.example.farmBackend.dao;

import com.example.farmBackend.entity.impl.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface FieldDAO extends JpaRepository<Field, String> {

}
