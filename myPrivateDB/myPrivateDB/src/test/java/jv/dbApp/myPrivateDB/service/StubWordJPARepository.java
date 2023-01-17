package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StubWordJPARepository implements WordJpaRepository {

    private Map<Long, Word> store = new HashMap<>();

    private Long index = 1L;

    @Override
    public Long getLastIndex() {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Word> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Word> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Word> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Word getOne(Long aLong) {
        return null;
    }

    @Override
    public Word getById(Long aLong) {
        return null;
    }

    @Override
    public Word getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Word> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Word> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Word> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Word> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Word> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Word> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Word, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Word> S save(S entity) {
        entity.setId(index);
        store.put(index++, entity);

        return entity;
    }

    @Override
    public <S extends Word> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Word> findById(Long aLong) {
        return Optional.of(store.get(aLong)) ;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Word> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Word> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        store.remove(aLong);
    }

    @Override
    public void delete(Word entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Word> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Word> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Word> findAll(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        long offset = pageable.getOffset();
        ArrayList<Word> words = new ArrayList<>(store.values());
        words.sort(new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.getId().compareTo(w2.getId());
            }
        });
        List<Word> result = words.subList((int) offset, (int) offset + pageSize);
        return new PageImpl<Word>(result);

    }
}
