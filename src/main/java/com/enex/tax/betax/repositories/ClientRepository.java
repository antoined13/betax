package com.enex.tax.betax.repositories;

import org.springframework.data.repository.CrudRepository;
import com.enex.tax.betax.beans.Client;

public interface ClientRepository extends CrudRepository<Client,Integer>{
    
}