package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.dto.QuizDto;
import jv.dbApp.myPrivateDB.dto.Status;
import jv.dbApp.myPrivateDB.dto.WordDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WordService {

    List<Word> getALlWords();

    Word getWord(Long id);

    Word saveWord(Word word);

    Word updateWord(Long id, WordDto wordDto);

    Long deleteWord(Long id);

    Status getStatus();

    Page<Word> getWordsAsPage(int page, int size);


    QuizDto getQuiz();

}
