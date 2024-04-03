package br.com.juciley.gestao_vagas.modules.candidate.controllers;

import br.com.juciley.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.juciley.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	private final CreateCandidateUseCase createCandidateUseCase;

	public CandidateController(CreateCandidateUseCase createCandidateUseCase) {
		this.createCandidateUseCase = createCandidateUseCase;
	}

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity createCandidateRequest) {
		try {
			var result = createCandidateUseCase.execute(createCandidateRequest);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/*@PostMapping("/")
	@Operation(summary = "Cadastro de candidato", description = "Essa função é responsável por cadastrar um candidato")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = CandidateEntity.class))}),
			@ApiResponse(responseCode = "400", description = "Usuário já existe")
	})
	public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity createCandidateRequest) {
		try {
			var result = this.createCandidateUseCase.execute(createCandidateRequest);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}*/
}
