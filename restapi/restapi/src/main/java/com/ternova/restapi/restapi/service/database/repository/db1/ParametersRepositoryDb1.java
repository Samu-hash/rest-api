package com.ternova.restapi.restapi.service.database.repository.db1;

import com.ternova.restapi.restapi.service.database.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametersRepositoryDb1 extends JpaRepository<Parameters, String> {

    @Query(value = "select * from parameters where id_param = :param1 OR id_param = :param2", nativeQuery = true)
    List<Parameters> getValuesCredentials(@Param("param1") String p1, @Param("param2") String p2);

    @Query(value = "select * from parameters where value_param = :value", nativeQuery = true)
    Parameters getValueCredential(@Param("value") String value);
}
