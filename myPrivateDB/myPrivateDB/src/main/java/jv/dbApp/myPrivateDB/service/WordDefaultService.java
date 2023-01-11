package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.dto.QuizDto;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
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
    public Page<Word> getAllWordsAsPage() {
        return wordJpaRepository.findAll(PageRequest.of(0, 4));
    }

    @Override
    public Page<Word> getWordsAsPage(int page, int size) {
        return wordJpaRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public QuizDto getQuiz() {
        Long lastIndex = wordJpaRepository.getLastIndex();
        Long[] indexArray = new Long[5];
        while(validateIndex(indexArray, lastIndex)){

        }
        // index 한 것 대로 뽑아서 Quiz 객체 생성
        Word[] words = new Word[5];


    }

    private boolean validateIndex(Long[] indexArray, Long lastIndex) {
        // 생각 해야할 오류 상황
        // 문제를 만들기에 단어가 부족한 경우
        Map<Long, Long> indexCountMap = new HashMap<>();

        for (int i = 0; i < indexArray.length; i++) {

        }

    }

    private Word findById(Long id) {
        return wordJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No such id : " + id)
        );
    }

    private boolean visitValidator(Long[] arr, Long index) {
        // validate logic
        return true;
    }

    private QuizDto makeQuiz(Word[] words) {
        QuizDto quiz = new QuizDto();
        int answerNum = new Random().ints(1, 0, 5).findAny().getAsInt();
        if (answerNum != 0) {
            Word temp = words[answerNum];
            words[answerNum] = words[0];
            words[0] = temp;
        }
        quiz.setConcept(words[0].getConcept());
        quiz.setAnswerIndex(answerNum);
        quiz.setQuestionArray(
                Arrays.stream(words).map(Word::getMeaning).toArray(String[]::new));
        return quiz;
    }
}
