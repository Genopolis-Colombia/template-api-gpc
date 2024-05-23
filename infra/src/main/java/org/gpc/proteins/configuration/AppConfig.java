package org.gpc.proteins.configuration;

import org.gpc.proteins.adapters.out.mysql.MysqlProteinRepositoryImpl;
import org.gpc.proteins.adapters.out.mysql.ProteinRepository;
import org.gpc.proteins.port.RepositoryPort;
import org.gpc.proteins.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    MysqlProteinRepositoryImpl getMysqlProteinRepositoryImpl(ProteinRepository proteinRepository){
        return new MysqlProteinRepositoryImpl(proteinRepository);
    }
    @Bean
    CreateProteinUseCaseImpl getCreateProteinUseCaseImpl(RepositoryPort repositoryPort){
        return new CreateProteinUseCaseImpl(repositoryPort);
    }

    @Bean
    GetProteinUseCaseImpl getProteinUseCase(RepositoryPort repositoryPort){
        return new GetProteinUseCaseImpl(repositoryPort);
    }

    @Bean
    DeleteProteinUseCaseImpl getDeleteProteinUseCase(RepositoryPort repositoryPort){
        return new DeleteProteinUseCaseImpl(repositoryPort);
    }
    @Bean
    UpdateProteinUseCaseImpl getPutProteinUseCase(RepositoryPort repositoryPort){
        return new UpdateProteinUseCaseImpl(repositoryPort);
    }
    @Bean
    ListProteinUseCaseImpl getListProteinUseCase(RepositoryPort repositoryPort){
        return new ListProteinUseCaseImpl(repositoryPort);
    }


}
