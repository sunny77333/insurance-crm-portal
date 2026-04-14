package com.insurance.crm.repository;

import com.insurance.crm.model.Adviser;
import com.insurance.crm.model.Client;
import com.insurance.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByAdviser(Adviser adviser);
    Optional<Client> findByUser(User user);
}