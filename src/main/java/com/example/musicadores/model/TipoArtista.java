package com.example.musicadores.model;

public enum TipoArtista {
    SOLO(1),
    BANDA(2),

    DUPLA(3);

    private Integer tipo;
    TipoArtista(Integer tipo) {
       this.tipo = tipo;
    }

    public static TipoArtista fromNumero(Integer numero){
        for(TipoArtista ta : TipoArtista.values()){
            if(ta.tipo == numero){
                return ta;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo encontrado para a String");
    }
}
