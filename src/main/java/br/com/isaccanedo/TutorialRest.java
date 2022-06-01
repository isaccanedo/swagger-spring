package br.com.isaccanedo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/api/tutorial")
public class TutorialRest
{
    private static Log logger = LogFactory.getLog(TutorialRest.class);

    @Autowired
    private TutorialRepository tutorialRepository;

    @ApiOperation(notes = "Retorna todos os tutoriais", value = "Retorna todos os tutoriais", nickname = "todosTutoriais")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @GetMapping(path="/tutoriais")
    public List<Tutorial> todosTutoriais()
    {
        return tutorialRepository.findAll();
    }

    @ApiOperation(notes = "Encontre um tutorial", value = "Retorne um determinado tutorial usando {tutorialId}", nickname = "encontrarTutorial")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Tutorial.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @GetMapping(path="/tutoriais/{tutorialId}")
    public Tutorial encontrarTutorial (@PathVariable Long tutorialId)
    {
        Tutorial tutorial = tutorialRepository.findOne(tutorialId);


        return tutorial;
    }

    @ApiOperation(notes = "Criar um novo tutorial", value = "Criar um novo tutorial", nickname = "criarTutorial")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criado", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 403, message = "Proibido"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Falha")})
    @PostMapping(path="/tuts")
    public ResponseEntity<Void> criarTutorial(@RequestBody Tutorial tutorial)
    {
        Tutorial emp = tutorialRepository.save(tutorial);

        // Build the location URI of the new item
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{tutorialId}")
                .buildAndExpand(emp.getId())
                .toUri();

        // Explicitly create a 201 Created response
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(notes = "Atualizar um tutorial existente", value = "Atualizar um funcionário existente", nickname = "atualizarTutorial")
    @PutMapping(path="/tuts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarTutorial(@RequestBody Tutorial tutorial)
    {
       tutorialRepository.save(tutorial);
    }

    @ApiOperation(notes = "Excluir um funcionário", value = "Excluir um funcionário usando {funcionarioId}", nickname = "excluirTutorial")
    @DeleteMapping(path="/tuts/{tutorialId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTutorial(@PathVariable Long tutorialId)
    {
        Tutorial func = tutorialRepository.findOne(tutorialId);
        tutorialRepository.delete(func);
        logger.info("Funcionário com id: " + tutorialId + " excluído com sucesso!...");
    }

}
