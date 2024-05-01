package org.gpc.proteins.configuration;

import org.gpc.proteins.adapters.out.mysql.MysqlPetRepositoryImpl;
import org.gpc.proteins.adapters.out.mysql.PetRepository;
import org.gpc.proteins.port.RepositoryPort;
import org.gpc.proteins.usecase.CreateProteinUseCaseImpl;
import org.gpc.proteins.usecase.DeleteProteinUseCaseImpl;
import org.gpc.proteins.usecase.GetProteinUseCaseImpl;
import org.gpc.proteins.usecase.PutProteinUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    MysqlPetRepositoryImpl getMysqlPetRepositoryImpl(PetRepository petRepository){
        return new MysqlPetRepositoryImpl(petRepository);
    }
    @Bean
    CreateProteinUseCaseImpl getCreatePetUseCaseImpl(RepositoryPort repositoryPort){
        return new CreateProteinUseCaseImpl(repositoryPort);
    }

    @Bean
    GetProteinUseCaseImpl getPetUseCase(RepositoryPort repositoryPort){
        return new GetProteinUseCaseImpl(repositoryPort);
    }

    @Bean
    DeleteProteinUseCaseImpl getDeletePetUseCase(RepositoryPort repositoryPort){
        return new DeleteProteinUseCaseImpl(repositoryPort);
    }
    @Bean
    PutProteinUseCaseImpl getPutPetUseCase(RepositoryPort repositoryPort){
        return new PutProteinUseCaseImpl(repositoryPort);
    }


}
