package com.gatos.michis.Repository;

import org.springframework.data.repository.CrudRepository;
import com.gatos.michis.Model.Michi;

public interface MichiRepository extends CrudRepository <Michi, Integer> {
    
}
