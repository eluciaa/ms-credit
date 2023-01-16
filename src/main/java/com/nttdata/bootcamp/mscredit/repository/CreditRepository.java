package com.nttdata.bootcamp.mscredit.repository;

import com.nttdata.bootcamp.mscredit.entity.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, Integer> {
}
