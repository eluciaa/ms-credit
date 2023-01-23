package com.nttdata.bootcamp.mscredit.service;

import com.nttdata.bootcamp.mscredit.entity.Credit;
import com.nttdata.bootcamp.mscredit.repository.CreditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CreditServiceImpl implements CreditService{

    @Autowired
    private CreditRepository creditRepository;
    @Override
    public Flux<Credit> getAll() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> getCreditById(Integer creditId) {
        return creditRepository.findById(creditId);
    }

    @Override
    public Mono<Credit> saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public Mono<Credit> updateCredit(Credit credit) {
        return creditRepository.findById(credit.getId())
                .flatMap(newCredit -> {
                    newCredit.setId(credit.getId());
                    newCredit.setCardNumber(credit.getCardNumber());
                    newCredit.setCreditLimit(credit.getCreditLimit());
                    newCredit.setExpiryDate(credit.getExpiryDate());
                    newCredit.setAvailableBalance(credit.getAvailableBalance());
                    return creditRepository.save(newCredit);
                });
    }

    @Override
    public Mono<Credit> deleteCredit(Integer customerId, Integer creditId) {
        return creditRepository.findById(creditId)
                .flatMap(credit -> creditRepository.delete(credit).then(Mono.just(credit)));
    }

}
