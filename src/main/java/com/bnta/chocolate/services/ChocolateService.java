package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    public List<Chocolate> findAllChocolates(){
        return chocolateRepository.findAll();
    }

    public Optional<Chocolate> findChocolateById(long id){
        return chocolateRepository.findById(id);
    }

    public Chocolate addNewChocolate(Chocolate chocolate){
        return chocolateRepository.save(chocolate);
    }

    public List<Chocolate> findChocolatesByMinimumCocoa(int cocoaPercentage){
        return chocolateRepository.findByCocoaPercentageGreaterThan(cocoaPercentage);
    }

}
