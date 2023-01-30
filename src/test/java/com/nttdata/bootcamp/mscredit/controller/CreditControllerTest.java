package com.nttdata.bootcamp.mscredit.controller;

import com.nttdata.bootcamp.mscredit.entity.Credit;
import com.nttdata.bootcamp.mscredit.service.CreditService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebFluxTest(CreditController.class)
public class CreditControllerTest {

    @MockBean
    private CreditService creditService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAll(){
        Flux<Credit> creditFlux = Flux.just(new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO"), new Credit(2, "3401-3904-3535-3775", 150000F, "20/08/2025",
                        0F, "PRESTAMO"));

         when(creditService.getAll()).thenReturn(creditFlux);

        Flux<Credit> responseBody = webTestClient.get()
                .uri("/credits")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Credit.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                        250.20F, "TARJETA DE CREDITO"))
                .expectNext(new Credit(2, "3401-3904-3535-3775", 150000F, "20/08/2025",
                        0F, "PRESTAMO"))
                .verifyComplete();
    }

    @Test
    void getCreditById(){
        Credit credit = new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO");

        Mono<Credit> creditMono = Mono.just(new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO"));

        when(creditService.getCreditById(credit.getId())).thenReturn(creditMono);

        webTestClient.get()
                .uri("/credits/customer/" + credit.getId())
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Credit.class)
                .getResponseBody();

        StepVerifier.create(creditMono)
                .expectNext(credit)
                .verifyComplete();

    }

    @Test
    void saveCredit(){

        Credit credit = new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO");

        Mono<Credit> creditMono = Mono.just(new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO"));

        when(creditService.saveCredit(credit)).thenReturn(creditMono);

        webTestClient.post()
                .uri("/credits")
                .accept(MediaType.APPLICATION_NDJSON)
                .body(creditMono, Credit.class)
                .exchange()
                .expectStatus().isCreated();

        StepVerifier.create(creditMono)
                .expectNext(credit)
                .verifyComplete();

    }

    @Test
    void updateCredit(){

        Credit credit = new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO");

        Mono<Credit> creditMono = Mono.just(new Credit(1, "3403-3904-3535-3775", 2500F, "20/08/2025",
                250.20F, "TARJETA DE CREDITO"));

        when(creditService.updateCredit(credit)).thenReturn(creditMono);

        webTestClient.put()
                .uri("/credits")
                .accept(MediaType.APPLICATION_NDJSON)
                .body(creditMono, Credit.class)
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(creditMono)
                .expectNext(credit)
                .verifyComplete();

    }

    @Test
    void deleteCredit(){
        given(creditService.deleteCredit(any())).willReturn(Mono.empty());
        webTestClient.delete()
                .uri("/credits/1")
                .exchange()
                .expectStatus().isOk();
    }
}
