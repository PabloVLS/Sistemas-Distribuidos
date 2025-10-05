package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
