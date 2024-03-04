package com.example.musicadores;

import com.example.musicadores.principal.Principal;
import com.example.musicadores.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicadoresApplication implements CommandLineRunner {
	@Autowired
	MusicRepository musicRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(MusicadoresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(musicRepositorio);
		principal.exibeMenu();
	}
}
