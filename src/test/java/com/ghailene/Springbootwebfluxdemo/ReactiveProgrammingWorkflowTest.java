package com.ghailene.Springbootwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveProgrammingWorkflowTest {

    @Test
    public void monoTest() {
        Mono<Integer> justInt = Mono.just(1).log();

        /** error case **/
        // Mono<?>  justInt =   Mono.just(1).then(Mono.error(new RuntimeException("Mono Exception"))).log();

        justInt.subscribe(System.out::println);

    }


    @Test
    public void fluxTest() {
        Flux<?> flux = Flux.just(1, 2, 3, 4).log();
        /** error case **/
        //Flux<?> fluxError = Flux.just(1,2,3,4).concatWith(Flux.error(new RuntimeException("Mono Exception"))).log();
        
        flux.subscribe(System.out::println);

    }

}
