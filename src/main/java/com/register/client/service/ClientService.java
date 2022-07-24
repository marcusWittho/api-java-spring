package com.register.client.service;

import com.register.client.model.Client;
import com.register.client.repository.ClientRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Classe Service.
 */
@Service
public class ClientService {

  final ClientRepository repository;
  
  public ClientService(ClientRepository clientRepository) {
    super();
    this.repository = clientRepository;
  }
  
  @Transactional
  public Client save(Client client) {
    return repository.save(client);
  }
  
  public Iterable<Client> findAll() {
    return repository.findAll();
  }
  
  public Optional<Client> findById(Long id) {
    return repository.findById(id);
  }
  
  @Transactional
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
  
  /**
   * Método responsável por atualizar o cliente pelo ID informado. 
   */
  public Optional<Object> updateClient(Long id, Client client) {
    return repository.findById(id)
        .map(clientFound -> {
          clientFound.setName(client.getName());
          clientFound.setAge(client.getAge());
          clientFound.setEmail(client.getEmail());
          return repository.save(clientFound);
        });
  }
}
