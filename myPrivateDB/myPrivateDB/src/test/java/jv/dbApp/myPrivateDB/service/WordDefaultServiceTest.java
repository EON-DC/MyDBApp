package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.dto.QuizDto;
import jv.dbApp.myPrivateDB.dto.Status;
import jv.dbApp.myPrivateDB.dto.WordDto;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WordDefaultServiceTest {

     StubWordJPARepository stubWordJPARepository;
     WordDefaultService wordDefaultService;

    public WordDefaultServiceTest() {
        stubWordJPARepository = new StubWordJPARepository();
        wordDefaultService = new WordDefaultService(stubWordJPARepository);
    }

    @BeforeEach
    void setUp() {
        stubWordJPARepository.save(new Word("IT", "data"));
        stubWordJPARepository.save(new Word("Science", "과학"));
        stubWordJPARepository.save(new Word("Art", "미술"));
        stubWordJPARepository.save(new Word("Social", "사회"));
        stubWordJPARepository.save(new Word("PE", "체육"));

    }

    @Test
    void getALlWords() {
        List<Word> words = wordDefaultService.getALlWords();
        assertThat(words.size()).isEqualTo(5);
        List<String> concepts = words.stream().map(Word::getConcept).toList();
        assertThat(concepts.contains("IT")).isTrue();
        assertThat(concepts.contains("Science")).isTrue();
    }

    @Test
    void getWord() {
        Word it = wordDefaultService.getWord(1L);
        Word science = wordDefaultService.getWord(2L);
        assertThat(it.getConcept()).isEqualTo("IT");
        assertThat(science.getConcept()).isEqualTo("Science");
    }

    @Test
    void saveWord() {
        Word word = wordDefaultService.saveWord(new Word("Math", "수학"));
        assertThat(word.getId()).isNotNull();
        assertThat(word.getMeaning()).isEqualTo("수학");
        assertThat(word.getConcept()).isEqualTo("Math");
    }

    @Test
    void updateWord() {
        Word word = wordDefaultService.updateWord(1L, new WordDto("음악", "Music", null, null
        ));
        assertThat(word.getId()).isNotNull();
        assertThat(word.getMeaning()).isEqualTo("음악");
        assertThat(word.getConcept()).isEqualTo("Music");
    }

    @Test
    void deleteWord() {
        wordDefaultService.deleteWord(1L);
        assertThrows(RuntimeException.class, () ->
                wordDefaultService.getWord(1L));
    }

    @Test
    void getWordsAsPage() {
        Page<Word> wordsAsPage = wordDefaultService.getWordsAsPage(0, 1);
        assertThat(wordsAsPage.getTotalElements()).isEqualTo(1);
        assertThat(wordsAsPage.get().toList().get(0).getConcept()).isEqualTo("IT");
        assertThat(wordsAsPage.get().toList().get(0).getMeaning()).isEqualTo("data");
    }

    @Test
    void getQuiz() {
        QuizDto quiz = wordDefaultService.getQuiz();
        System.out.println("quiz.toString() = " + quiz.toString());
        assertThat(quiz.getConcept()).isNotNull();
    }

    @Test
    void getStatus(){
        Status status = wordDefaultService.getStatus();
        assertThat(status.getRowCount()).isEqualTo(5);
    }
}