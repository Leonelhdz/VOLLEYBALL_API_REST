package com.example.volleyball_api_rest.Repository;
import com.example.volleyball_api_rest.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNombre(String nombre);

    List<User> findUsersByClubId(Integer clubId);

    @Query("SELECT u FROM User u JOIN Categoria c ON u.categoria_id = c.id WHERE c.id = ?1")
    List<User> findUsersByCategoria_id(Integer categoria_id);

    User findByEmail(String email);

    Optional<User> findUserByEmail(String email);

    User save(User user);

    void delete(User user);
}
