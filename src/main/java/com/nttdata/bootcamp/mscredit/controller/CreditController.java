package com.nttdata.bootcamp.mscredit.controller;

import com.nttdata.bootcamp.mscredit.entity.Credit;
import com.nttdata.bootcamp.mscredit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/credits")
public class CreditController {

    @Autowired
    private CreditService creditService;

    /**
    *
    * Obtener todas los creditos bancarias
    * @return
    */
   @GetMapping
   public Flux<Credit> getAll(){
       return creditService.getAll();
   }

   /**
    *
    * Obtene creditos bancaria por Id
    * @param id
    * @return
    */
   @GetMapping ("/{id}")
   public Mono<Credit> getCreditById(@PathVariable Integer id){
       return creditService.getCreditById(id);
   }


   /**
    * Registrar cuenta credit
    * @param credit
    * @return
    */
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Mono<Credit> saveAccount(@RequestBody Credit credit){
       return creditService.saveCredit(credit);
   }

   /**
    * Modificar cuenta credito
    * @param credit
    * @return
    */
   @PutMapping
   public Mono<Credit> updateCredit(@RequestBody Credit credit){
       return creditService.updateCredit(credit);
   }

   /**
    * Eliminar cuenta credito
    * @param id
    * @return
    */
   @DeleteMapping("/{id}")
   public Mono<Credit> deleteCredit(@PathVariable Integer id){
       return creditService.deleteCredit(id);
   }
   
   @GetMapping ("/customer/{id}")
   public Flux<Credit> getCreditByCustomerId(@PathVariable String id){
       return creditService.getCreditByCustomerId(id);
   }

}
