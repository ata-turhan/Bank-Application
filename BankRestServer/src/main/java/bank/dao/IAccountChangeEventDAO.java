package bank.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import bank.domain.AccountChangeEvent;

public interface IAccountChangeEventDAO extends JpaRepository<AccountChangeEvent, Long> {

}
