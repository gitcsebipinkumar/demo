package bipin.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import bipin.springframework.domain.CardType;

@RepositoryRestResource
public interface CardTypeRepository extends CrudRepository<CardType, Integer>{
}
