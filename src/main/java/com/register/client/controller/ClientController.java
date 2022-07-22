package com.register.client.controller;

import com.register.client.model.Client;
import com.register.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller.
 */
@RestController
public class ClientController {
  
  @Autowired
  private ClientRepository repository;

  /**
   * Método responsável por adicionar um novo cliente. 
   */
  @GetMapping("/save")
  public String addClient() {
    
    repository.save(new Client("Cliente 1", 10, "cliente1@email.com"));
    repository.save(new Client("Cliente 2", 20, "cliente2@email.com"));
    repository.save(new Client("Cliente 3", 30, "cliente3@email.com"));
    repository.save(new Client("Cliente 4", 40, "cliente4@email.com"));
    repository.save(new Client("Cliente 5", 50, "cliente5@email.com"));
    
    return "Client added.";
  }
  
  @GetMapping("/clients")
  public Iterable<Client> clients() {
    return repository.findAll();
  }
  
  @GetMapping("/client/{id}")
  public Client client(@PathVariable long id) {
    return repository.findById(id).orElse(new Client());
  }
}
