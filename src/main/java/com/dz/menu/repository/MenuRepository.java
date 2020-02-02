package com.dz.menu.repository;

import com.comedor.menu.Menu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends ReactiveMongoRepository<Menu, String> { }
