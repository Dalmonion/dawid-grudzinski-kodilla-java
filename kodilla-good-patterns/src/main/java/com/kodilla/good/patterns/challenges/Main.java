package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
public class Main {

    public static void main(String[] args) {
        MovieStore movieStore = new MovieStore();

        String result = movieStore.getMovies().entrySet().stream()
                .flatMap(movie -> movie.getValue().stream())
                .collect(Collectors.joining("! ", "", "!"));

        log.info(result);
    }
}
