package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.dto.QuizDto;
import jv.dbApp.myPrivateDB.dto.Status;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class WordDefaultService implements WordService {

    private final WordJpaRepository wordJpaRepository;

    @Override
    public List<Word> getALlWords() {
        return wordJpaRepository.findAll();
    }

    @Override
    public Word getWord(Long id) {
        return findById(id);
    }

    @Override
    public Word saveWord(Word word) {
        return wordJpaRepository.save(word);
    }

    @Override
    public Word updateWord(Long id, String concept, String meaning) {
        Word findWord = findById(id);
        findWord.update(concept, meaning);
        return findWord;
    }

    @Override
    public Long deleteWord(Long id) {
        wordJpaRepository.deleteById(id);
        return id;
    }



    @Override
    public Page<Word> getWordsAsPage(int page, int size) {
        return wordJpaRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public QuizDto getQuiz() {
        return makeQuiz();
    }

    @Override
    public Status getStatus() {
        return new Status(wordJpaRepository.findAll().size());
    }

    private Word findById(Long id) {
        return wordJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such id : " + id)
        );
    }

    private QuizDto makeQuiz() {

        List<Word> words = wordJpaRepository.findAll();
        HashSet<Word> selectedWords = new HashSet<>();
        while (selectedWords.size() < 5) {
            int index = (int) (Math.random() * words.size());
            selectedWords.add(words.get(index));
        }

        return new QuizDto(selectedWords.toArray(Word[]::new));
    }
}
