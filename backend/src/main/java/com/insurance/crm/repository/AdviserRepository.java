package com.insurance.crm.repository;

import com.insurance.crm.model.Adviser;
import com.insurance.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdviserRepository extends JpaRepository<Adviser, Long> {
    Optional<Adviser> findByUser(User user);
}