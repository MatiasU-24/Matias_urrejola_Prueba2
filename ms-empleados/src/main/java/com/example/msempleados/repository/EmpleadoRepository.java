package com.example.msempleados.repository;

import com.example.msempleados.entity.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    @Query(value = "SELECT * FROM empleados WHERE activo = true AND YEAR(fecha_contratacion) = :anio", nativeQuery = true)
    List<Empleado> listarEmpleadosActivosPorAnio(@Param("anio") Integer anio);
}
