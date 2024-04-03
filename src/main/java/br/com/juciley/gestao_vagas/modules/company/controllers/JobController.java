package br.com.juciley.gestao_vagas.modules.company.controllers;

import br.com.juciley.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.juciley.gestao_vagas.modules.company.entities.JobEntity;
import br.com.juciley.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

	private final CreateJobUseCase createJobUseCase;

	public JobController(CreateJobUseCase createJobUseCase) {
		this.createJobUseCase = createJobUseCase;
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
		var companyId = request.getAttribute("company_id");

		try {
			var jobEntity = JobEntity.builder()
					.benefits(createJobDTO.getBenefits())
					.companyId(UUID.fromString(companyId.toString()))
					.description(createJobDTO.getDescription())
					.level(createJobDTO.getLevel())
					.build();

			var result = this.createJobUseCase.execute(jobEntity);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
