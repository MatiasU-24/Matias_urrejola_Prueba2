package com.example.msclientes.repository;

import com.example.msclientes.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByEmailContainingIgnoreCase(String texto);
}
