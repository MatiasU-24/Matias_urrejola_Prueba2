package com.example.mssucursales.repository;

import com.example.mssucursales.entity.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    @Query(value = "SELECT * FROM sucursales WHERE operativa = true ORDER BY nombre ASC", nativeQuery = true)
    List<Sucursal> listarSucursalesOperativas();
}
