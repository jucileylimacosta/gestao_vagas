package br.com.juciley.gestao_vagas.modules.company.useCases;

import br.com.juciley.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.juciley.gestao_vagas.modules.company.entities.JobEntity;
import br.com.juciley.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.juciley.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

	private final JobRepository jobRepository;

	private final CompanyRepository companyRepository;

	public CreateJobUseCase(JobRepository jobRepository, CompanyRepository companyRepository) {
		this.jobRepository = jobRepository;
		this.companyRepository = companyRepository;
	}

	public JobEntity execute(JobEntity jobEntity) {
		companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
		return jobRepository.save(jobEntity);
	}
}
