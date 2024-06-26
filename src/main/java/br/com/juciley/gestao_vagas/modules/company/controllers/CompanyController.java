package br.com.juciley.gestao_vagas.modules.company.controllers;

import br.com.juciley.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.juciley.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

	private final CreateCompanyUseCase createCompanyUseCase;

	public CompanyController(CreateCompanyUseCase createCompanyUseCase) {
		this.createCompanyUseCase = createCompanyUseCase;
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
		try {
			var result = createCompanyUseCase.execute(companyEntity);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
