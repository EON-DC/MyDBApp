package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.dto.QuizDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WordService {

    List<Word> getALlWords();

    Word getWord(Long id);

    Word saveWord(Word word);

    Word updateWord(Long id, String concept, String meaning);

    Long deleteWord(Long id);

    Page<Word> getAllWordsAsPage();

    Page<Word> getWordsAsPage(int page, int size);


    QuizDto getQuiz();

}
