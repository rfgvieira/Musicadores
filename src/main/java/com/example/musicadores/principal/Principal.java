package com.example.musicadores.principal;

import com.example.musicadores.model.Artista;
import com.example.musicadores.model.Musica;
import com.example.musicadores.repository.MusicRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private MusicRepository musicRepositorio;

    private List<Artista> artistaList;
    public Principal(MusicRepository musicRepositorio) {
        this.musicRepositorio = musicRepositorio;
    }


    public void exibeMenu(){
        var opcao = -1;
        while (opcao!=0){
            var menu = """
                Menu
                1 - Cadastrar artista
                2 - Cadastrar música
                3 - Listar artistas
                4 - Listar músicas
                5 - Buscar música por artista
                0 - Sair
                """;

            System.out.println(menu);


            System.out.print("Digite a opção desejada: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao){
                case 1 :
                    cadastrarArtista();
                    break;
                case 2 :
                    cadastrarMusica();
                    break;
                case 3 :
                    listarArtista();
                    break;
                case 4 :
                    listarMusica();
                    break;
                case 5 :
                    buscaMusicaPorArtista();
                    break;
                case 0 :
                    break;
                default:
                    System.out.println("Opção não encontrada!");
            }
        }


    }

    private void cadastrarArtista() {
        System.out.print("Digite o nome do artista desejado: ");
        var nome = sc.nextLine();

        System.out.println("""
                1 - Solo
                2 - Banda
                3 - Dupla
                """);
        System.out.print("Digite o tipo do artista dentre as opções acima: ");
        var tipo = Integer.parseInt(sc.nextLine());
        Artista artista = new Artista(nome, tipo);
        musicRepositorio.save(artista);
    }

    private void cadastrarMusica() {
        System.out.print("Digite o nome da música desejado: ");
        var nome = sc.nextLine();

        System.out.print("Digite o nome do álbum da música: ");
        var album = sc.nextLine();

        System.out.print("Digite o nome do artista: ");
        var nomeArtista = sc.nextLine();

        var artistaBusca = musicRepositorio.buscaArtistaPorNome(nomeArtista);

        if(artistaBusca.isPresent()){
            var artista = artistaBusca.get();
            Musica musica = new Musica(nome, album, artista);
            artista.setMusicas(musica);
            musicRepositorio.save(artista);
        } else{
            System.out.println("Artista não encontrado, adicione o artista antes de adicionar a música dele!");
        }
    }

    private void listarArtista() {
        artistaList = musicRepositorio.listarArtistas();
        artistaList.forEach(a -> {
            System.out.println("Nome: " + a.getNome() + " - " + a.getTipoArtista());
        });
    }

    private void listarMusica() {
        List<Musica> musicas = musicRepositorio.listaMusicas();
        musicas.forEach(m ->{
            System.out.println(m.getArtista().getNome() + " - " + m.getNome() + " Álbum: " + m.getAlbum());
        });
    }

    private void buscaMusicaPorArtista() {
        listarArtista();
        System.out.print("Digite o nome do artista desejado dentre os acimas: ");
        var nomeArtista = sc.nextLine();
        Optional<Artista> artistaBuscado = artistaList.stream().filter(a-> a.getNome().toLowerCase().contains(nomeArtista.toLowerCase())).findFirst();
        if(artistaBuscado.isPresent()){
            var artista = artistaBuscado.get();
            List<Musica> musicas = musicRepositorio.buscaMusicaPorArtista(artista);
            musicas.forEach(System.out::println);
        } else{
            System.out.println("Nenhum artista encontrado com esse nome!");
        }
    }
}
