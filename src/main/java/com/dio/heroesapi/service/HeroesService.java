package com.dio.heroesapi.service;

import com.dio.heroesapi.document.Heroes;
import com.dio.heroesapi.repository.HeroesRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Data
@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public Flux<Heroes> findAll() {
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findHeroesById(String id) {
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> criaHeroi(Heroes heroes) {
        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono<Boolean> deletaHeroi(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
