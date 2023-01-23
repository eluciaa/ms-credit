package com.nttdata.bootcamp.mscredit.service;

import com.nttdata.bootcamp.mscredit.entity.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Flux<Credit> getAll();
    Mono<Credit> getCreditById(Integer creditId);
    Mono<Credit> saveCredit(Credit credit);
    Mono<Credit> updateCredit(Credit credit);
    Mono<Credit> deleteCredit(Integer customerId, Integer creditId);

}
