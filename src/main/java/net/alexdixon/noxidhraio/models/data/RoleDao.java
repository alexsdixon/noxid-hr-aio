package net.alexdixon.noxidhraio.models.data;


import net.alexdixon.noxidhraio.models.forms.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleDao extends CrudRepository<Role, Integer> {


}
