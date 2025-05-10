package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public record SerieDTO(
        long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse) {
}
