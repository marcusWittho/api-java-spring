package com.register.client.controller;

import com.register.client.model.Client;
import com.register.client.repository.ClientRepository;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller.
 */
@RestController
@RequestMapping(path = "/register")
public class ClientController {
  
  private ClientRepository repository;
  
  public ClientController(ClientRepository repository) {
    super();
    this.repository = repository;
  }

  /**
   * Método responsável por adicionar um novo cliente. 
   */
  @PostMapping
  public ResponseEntity<Client> addClient(@RequestBody Client client) {
    
    return ResponseEntity.status(200).body(repository.save(client));
  }
  
  /**
   * Método responsável por retornar uma lista com todos os clientes cadastrados. 
   */
  @GetMapping
  public ResponseEntity<Iterable<Client>> clients() {

    Iterable<Client> clients = new ArrayList<>();
    clients = repository.findAll();
    
    return ResponseEntity.status(201).body(clients);
  }
  
  /**
   * Método responsável por retornar o cliente com o ID informado por parâmetro. 
   */
  @GetMapping("/{id}")
  public ResponseEntity<Optional<Client>> getClient(@PathVariable long id) {

    Optional<Client> client;
    
    try {
      client = repository.findById(id);
      return ResponseEntity.status(201).body(client);
    } catch (NoSuchElementException err) {
      return ResponseEntity.status(404).build();
    }
  }
  
  /**
   * Método responsável por remover o cliente com o ID informado por parâmetro. 
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Optional<Client>> removeClient(@PathVariable Long id) {
    try {
      repository.deleteById(id);
      return ResponseEntity.status(201).build();
    } catch (NoSuchElementException err) {
      return ResponseEntity.status(404).build();
    }
  }
  
  /**
   * Método responsável por atualizar determinado cliente.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
    
    return repository.findById(id)
        .map(foundClient -> {
          foundClient.setName(client.getName());
          foundClient.setAge(client.getAge());
          foundClient.setEmail(client.getEmail());
          Client clientUpdated = repository.save(foundClient);
          return ResponseEntity.ok().body(clientUpdated);
        }).orElse(ResponseEntity.notFound().build());
  }
}
