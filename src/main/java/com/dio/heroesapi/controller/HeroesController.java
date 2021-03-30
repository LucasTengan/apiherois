package com.dio.heroesapi.controller;

import com.dio.heroesapi.document.Heroes;
import com.dio.heroesapi.service.HeroesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.dio.heroesapi.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;


@RestController
@RequiredArgsConstructor
@RequestMapping(HEROES_ENDPOINT_LOCAL)
public class HeroesController {
    private final HeroesService heroesService;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems() {
        log.info("requesting the list off all heroes");
        return heroesService.findAll();

    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
        log.info("Requesting the hero with id {}", id);
        return heroesService.findHeroesById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was Created");
        return heroesService.criaHeroi(heroes);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deletebyIDHero(@PathVariable String id) {
        heroesService.deletaHeroi(id);
        log.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
