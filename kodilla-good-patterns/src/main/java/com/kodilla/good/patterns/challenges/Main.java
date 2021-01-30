package com.kodilla.good.patterns.challenges;

public class Main {

    public static void main(String[] args) {
        MovieStore movieStore = new MovieStore();

        movieStore.getMovies().entrySet().stream()
                .flatMap(movie -> movie.getValue().stream())
                .forEach(movie -> System.out.print(movie + "! "));
    }
}
