package io.lrsystem.crtransito.common;

import io.lrsystem.crtransito.api.model.VeiculoModel;
import io.lrsystem.crtransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Veiculo.class, VeiculoModel.class)
                .addMappings(mapper -> mapper.map(
                        Veiculo::getPlaca, VeiculoModel::setPlacaVeiculo));

        return new ModelMapper();
    }

}
