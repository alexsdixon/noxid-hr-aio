package net.alexdixon.noxidhraio.models.data;



import net.alexdixon.noxidhraio.models.forms.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername (String Username);



}
