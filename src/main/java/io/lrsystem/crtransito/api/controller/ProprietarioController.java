package io.lrsystem.crtransito.api.controller;

import io.lrsystem.crtransito.domain.model.Proprietario;
import io.lrsystem.crtransito.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> lista(){
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@RequestBody Proprietario proprietario){
        return proprietarioRepository.save(proprietario);
    }

//    @PutMapping("/{proprietarioId}")
//    public ResponseEntity<Proprietario> atulizar(@PathVariable Long proprietarioId,
//                                                 @RequestBody Proprietario proprietario){
//
//    }

}
