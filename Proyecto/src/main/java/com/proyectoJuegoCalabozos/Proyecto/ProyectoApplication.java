package com.proyectoJuegoCalabozos.Proyecto;

import com.proyectoJuegoCalabozos.Proyecto.Db.FakeDb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Bean
	public FakeDb createDb()
	{
		return new FakeDb();
	}

}
