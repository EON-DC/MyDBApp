package jv.dbApp.myPrivateDB.repository;

import jv.dbApp.myPrivateDB.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByCategorySector(String categorySector);
}
