package io.lrsystem.crtransito.domain.service;

import io.lrsystem.crtransito.domain.exception.NegocioException;
import io.lrsystem.crtransito.domain.model.Proprietario;
import io.lrsystem.crtransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario){
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if (emailEmUso){
            throw new NegocioException("Já existe um proprietario com esse email");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietario){
        proprietarioRepository.deleteById(proprietario);
    }

}
