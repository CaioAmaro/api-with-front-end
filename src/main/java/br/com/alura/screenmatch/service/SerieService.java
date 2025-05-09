package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    private List<SerieDTO> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getAtores(),
                        s.getPoster(),
                        s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(serieRepository.lancamentosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getAvaliacao(),
                    s.getGenero(),
                    s.getAtores(),
                    s.getPoster(),
                    s.getSinopse());
        }

        return null;
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e ->
                            new EpisodioDTO(
                                    e.getTemporada(),
                                    e.getTitulo(),
                                    e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporadasPorNumeros(Long id, Long numero) {
        List<Episodio> listEpisodios = serieRepository.obterEpisodiosPorTemporada(id,numero);
        return listEpisodios.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String categoria) {
        Categoria nomeCategoria = Categoria.fromPortugues(categoria);
        return converteDados(serieRepository.findByGenero(nomeCategoria));
    }
}
