package com.example.musicadores.repository;

import com.example.musicadores.model.Artista;
import com.example.musicadores.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT a FROM Artista a")
    List<Artista> listarArtistas();

    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE :nome")
    Optional<Artista> buscaArtistaPorNome(String nome);

    @Query("SELECT m from Artista a Join a.musicas m ORDER BY a.nome")
    List<Musica> listaMusicas();

    @Query("SELECT m from Artista a Join a.musicas m WHERE a = :artista")
    List<Musica> buscaMusicaPorArtista(Artista artista);
}
