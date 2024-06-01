package bank.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import bank.domain.Account;

public interface IAccountDAO extends JpaRepository<Account, Long> {

}
