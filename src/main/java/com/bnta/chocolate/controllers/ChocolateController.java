package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.services.ChocolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chocolates")
public class ChocolateController {

    @Autowired
    ChocolateService chocolateService;

//    Handles following:
//    * GET /chocolates
//    * GET /chocolates?cocoaPercentage=69
    @GetMapping
    public ResponseEntity<List<Chocolate>> getAllChocolatesAndFilters(
            @RequestParam(required = false, name = "cocoaPercentage") Integer cocoaPercentage
    ){
        // GET /chocolates?cocoaPercentage=69
        if(cocoaPercentage != null){
            return new ResponseEntity<>(
                    chocolateService.findChocolatesByMinimumCocoa(cocoaPercentage),
                    HttpStatus.OK
            );
        }
        // GET /chocolates
        return new ResponseEntity<>(
                chocolateService.findAllChocolates(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Chocolate> getChocolate(@PathVariable Long id){
        Optional<Chocolate> chocolate = chocolateService.findChocolateById(id);
        if (chocolate.isPresent()){
            return new ResponseEntity<>(chocolate.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Chocolate> postChocolate(@RequestBody Chocolate chocolate){
        chocolateService.addNewChocolate(chocolate);
        return new ResponseEntity<>(chocolate, HttpStatus.CREATED);
    }


}
