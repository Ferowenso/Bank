package com.example.bank.controllers;


import com.example.bank.models.Credit;
import com.example.bank.services.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/credits")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Credit> getCredit(@PathVariable Long id){
        Credit credit = creditService.getCreditById(id);
        return ResponseEntity.ok().body(credit);
    }

    @GetMapping("")
    ResponseEntity<List<Credit>> getAllCredits(){
        return ResponseEntity.ok().body(creditService.getAllCredits());
    }

    @PostMapping("")
    ResponseEntity<Credit> addCredit(@RequestBody @Valid Credit credit){
        return ResponseEntity.ok().body(creditService.addCredit(credit));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Credit> deleteCredit(@PathVariable Long id){
        creditService.deleteCredit(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Credit> editCredit(@PathVariable Long id, @RequestBody @Valid Credit credit){
        return ResponseEntity.ok().body(creditService.editCredit(id, credit));
    }

}
