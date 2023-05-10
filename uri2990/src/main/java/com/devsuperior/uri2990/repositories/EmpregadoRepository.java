package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
    @Query(nativeQuery = true, value = "select empregados.cpf, empregados.enome, departamentos.dnome " +
            " from empregados  " +
            " inner join departamentos on empregados.dnumero = departamentos.dnumero " +
            " left  join trabalha on trabalha.cpf_emp = empregados.cpf " +
            " where trabalha.cpf_emp is null " +
            " order by  empregados.cpf")
    List<EmpregadoDeptProjection> search1();

    @Query("select  new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) " +
            " from Empregado obj  " +
            " where obj.cpf not in (" +
            "select obj.cpf " +
            "from Empregado obj " +
            "inner join obj.projetosOndeTrabalha " +
            ")" +
            " order by  obj.cpf")
    List<EmpregadoDeptDTO> search2();

}
