package jv.dbApp.myPrivateDB.repository;

import jv.dbApp.myPrivateDB.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordJpaRepository extends JpaRepository<Word, Long> {

    @Query(value = "select count(w) from Word w")
    Long getLastIndex();
}
