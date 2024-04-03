package br.com.juciley.gestao_vagas.modules.company.useCases;

import br.com.juciley.gestao_vagas.exceptions.UserFoundException;
import br.com.juciley.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.juciley.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

	private final CompanyRepository companyRepository;

	public CreateCompanyUseCase(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public CompanyEntity execute(CompanyEntity companyEntity) {

		companyRepository
			.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
			.ifPresent((user) -> {
				throw new UserFoundException();
			});

		/*var password = passwordEncoder.encode(companyEntity.getPassword());
		companyEntity.setPassword(password);*/

		return companyRepository.save(companyEntity);
	}
}