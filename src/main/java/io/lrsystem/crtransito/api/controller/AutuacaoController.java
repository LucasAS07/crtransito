package io.lrsystem.crtransito.api.controller;

import io.lrsystem.crtransito.api.assembler.AutuacaoAsembler;
import io.lrsystem.crtransito.api.model.AutuacaoModel;
import io.lrsystem.crtransito.api.model.input.AutuacaoInput;
import io.lrsystem.crtransito.domain.model.Autuacao;
import io.lrsystem.crtransito.domain.model.Veiculo;
import io.lrsystem.crtransito.domain.service.RegistroAutuacaoService;
import io.lrsystem.crtransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoAsembler autuacaoAsembler;
    private final RegistroVeiculoService registroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInput autuacaoInput){
        Autuacao novaAutuacao = autuacaoAsembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId,novaAutuacao);
        return autuacaoAsembler.toModel(autuacaoRegistrada);
    }

    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long veiculoId){
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return autuacaoAsembler.toCollectionModel(veiculo.getAutuacoes());
    }

}
