package com.example.msclientes.config;

import com.example.msclientes.entity.Cliente;
import com.example.msclientes.entity.Direccion;
import com.example.msclientes.repository.ClienteRepository;
import com.example.msclientes.repository.DireccionRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner cargarDatos(ClienteRepository clienteRepository, DireccionRepository direccionRepository) {
        return args -> {
            if (clienteRepository.count() == 0) {
                Cliente c1 = clienteRepository.save(new Cliente(null, "Ana", "Lopez", "ana.lopez@mail.com", "912345678", 120, true, LocalDate.now().minusMonths(8)));
                Cliente c2 = clienteRepository.save(new Cliente(null, "Pedro", "Rojas", "pedro.rojas@mail.com", "923456789", 60, true, LocalDate.now().minusMonths(5)));
                Cliente c3 = clienteRepository.save(new Cliente(null, "Camila", "Soto", "camila.soto@mail.com", "934567890", 200, true, LocalDate.now().minusMonths(2)));
                direccionRepository.save(new Direccion(null, "Av Providencia", 1200, "Providencia", "7500000", true, LocalDate.now().minusMonths(8), c1));
                direccionRepository.save(new Direccion(null, "Los Carrera", 455, "Concepcion", "4030000", true, LocalDate.now().minusMonths(5), c2));
                direccionRepository.save(new Direccion(null, "Av Alemania", 980, "Temuco", "4780000", true, LocalDate.now().minusMonths(2), c3));
            }
        };
    }
}
