package io.lrsystem.crtransito.api.controller;

import io.lrsystem.crtransito.api.assembler.VeiculoAssembler;
import io.lrsystem.crtransito.api.model.VeiculoModel;
import io.lrsystem.crtransito.api.model.input.VeiculoInput;
import io.lrsystem.crtransito.domain.model.Veiculo;
import io.lrsystem.crtransito.domain.repository.VeiculoRepository;
import io.lrsystem.crtransito.domain.service.ApreencaoVeiculoService;
import io.lrsystem.crtransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoRepository veiculoRepository;
    private final VeiculoAssembler veiculoAssembler;
    private final ApreencaoVeiculoService apreencaoVeiculoService;

    @GetMapping
    public List<VeiculoModel> listar(){
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId){
        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastro(@Valid @RequestBody VeiculoInput veiculoInput){
        Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
        Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);

        return veiculoAssembler.toModel(veiculoCadastrado);
    }

    @PutMapping("/{veiculoId}/apreencao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId){
        apreencaoVeiculoService.apreender(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/liberacao-apreencao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void liberarApreender(@PathVariable Long veiculoId){
        apreencaoVeiculoService.liberarApreencao(veiculoId);
    }


}
