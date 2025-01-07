package io.lrsystem.crtransito.api.controller;

import io.lrsystem.crtransito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @GetMapping("/proprietarios")
    public List<Proprietario> lista(){
        var proprietario = new Proprietario();
        proprietario.setId(1L);
        proprietario.setNome("Heitor Rodrigues");
        proprietario.setEmail("heitor@email.com");
        proprietario.setTelefone("37999998888");

        var proprietario1 = new Proprietario();
        proprietario1.setId(2L);
        proprietario1.setNome("João Lucas");
        proprietario1.setEmail("joao@email.com");
        proprietario1.setTelefone("37999889999");

        return Arrays.asList(proprietario,proprietario1);
    }

}
