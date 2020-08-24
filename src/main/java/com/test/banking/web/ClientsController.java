package com.test.banking.web;

import com.test.banking.dto.request.ClientRequestDto;
import com.test.banking.dto.request.ClientsFilter;
import com.test.banking.dto.response.ClientDto;
import com.test.banking.entity.Client;
import com.test.banking.service.ClientsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@Api(description = "Выполнение операций над клиентами")
@Validated
public class ClientsController {
    private ClientsService clientsService;

    private ModelMapper modelMapper;

    public ClientsController(ClientsService clientsService, ModelMapper modelMapper) {
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation("Создание клиента")
    @PostMapping("/")
    public ResponseEntity<ClientDto> createClient(@ApiParam("Запрос") @Valid @RequestBody ClientRequestDto request) throws URISyntaxException {
        Client client = clientsService.createClient(createEntity(request));
        return ResponseEntity.created(new URI("/clients/" + client.getId())).body(createDTO(client));
    }

    @ApiOperation("Редактирование клиента")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> editClient(@ApiParam("ID") @PathVariable Integer id, @ApiParam("Запрос") @Valid @RequestBody ClientRequestDto request) {
        Client newClient = createEntity(request);
        Client client = clientsService.updateClient(id, newClient);
        return ResponseEntity.ok(createDTO(client));
    }

    @ApiOperation("Удаление клиента")
    @DeleteMapping("/{id}")
    public ResponseEntity createClient(@ApiParam("ID") @PathVariable Integer id) {
        clientsService.deleteClientById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Получение списка клиентов")
    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> getClients(@Valid ClientsFilter filter) {
        List<Client> clients = clientsService.findClients(filter);
        return ResponseEntity.ok(createDTOs(clients));
    }

    @ApiOperation("Получение клиента")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClient(int id) {
        Client client = clientsService.findClient(id);
        return ResponseEntity.ok(createDTO(client));
    }

    private Client createEntity(ClientRequestDto dto) {
        return modelMapper.map(dto, Client.class);
    }

    private ClientDto createDTO(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    private List<ClientDto> createDTOs(List<Client> clients) {
        return clients.stream().map(client -> createDTO(client)).collect(Collectors.toList());
    }
}
