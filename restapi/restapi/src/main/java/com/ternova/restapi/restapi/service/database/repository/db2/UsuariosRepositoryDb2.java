package com.ternova.restapi.restapi.service.database.repository.db2;

import com.ternova.restapi.restapi.service.database.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepositoryDb2 extends JpaRepository<Usuarios, Integer> {

    @Query(nativeQuery = true, value = "select * from usuarios where id_usuario =:p")
    Usuarios findUser(@Param("p") Integer p);
}
