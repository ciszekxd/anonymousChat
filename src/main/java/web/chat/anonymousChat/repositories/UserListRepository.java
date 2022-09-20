package web.chat.anonymousChat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.chat.anonymousChat.DAOs.UserListDAO;

@Repository
public interface UserListRepository extends CrudRepository<UserListDAO, Integer> {
    @Override
    <S extends UserListDAO> S save(S entity);
}
