package br.com.juciley.gestao_vagas.modules.candidate.useCases;

import br.com.juciley.gestao_vagas.exceptions.UserFoundException;
import br.com.juciley.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.juciley.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

	private final CandidateRepository candidateRepository;

	public CreateCandidateUseCase(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}

	public CandidateEntity execute(CandidateEntity candidateEntity) {
		this.candidateRepository
				.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
				.ifPresent((user) -> {
					throw new UserFoundException();
				});

		/*var password = passwordEncoder.encode(candidateEntity.getPassword());
		candidateEntity.setPassword(password);*/

		return this.candidateRepository.save(candidateEntity);
	}
}
