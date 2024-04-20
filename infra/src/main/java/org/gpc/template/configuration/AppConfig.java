package org.gpc.template.configuration;

import org.gpc.template.adapters.out.mysql.MysqlPetRepositoryImpl;
import org.gpc.template.adapters.out.mysql.PetRepository;
import org.gpc.template.port.RepositoryPort;
import org.gpc.template.usecase.CreatePetUseCaseImpl;
import org.gpc.template.usecase.GetPetUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    MysqlPetRepositoryImpl getMysqlPetRepositoryImpl(PetRepository petRepository){
        return new MysqlPetRepositoryImpl(petRepository);
    }
    @Bean
    CreatePetUseCaseImpl getCreatePetUseCaseImpl(RepositoryPort repositoryPort){
        return new CreatePetUseCaseImpl(repositoryPort);
    }

    @Bean
    GetPetUseCaseImpl getPetUseCase(RepositoryPort repositoryPort){
        return new GetPetUseCaseImpl(repositoryPort);
    }
}
