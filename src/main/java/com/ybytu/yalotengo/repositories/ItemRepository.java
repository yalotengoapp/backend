package com.ybytu.yalotengo.repositories;
import com.ybytu.yalotengo.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUserUsername(String username);
}
