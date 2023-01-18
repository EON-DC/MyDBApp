package jv.dbApp.myPrivateDB.rest;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.dto.QuizDto;
import jv.dbApp.myPrivateDB.dto.WordDto;
import jv.dbApp.myPrivateDB.service.WordDefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordRestController {

    private final WordDefaultService wordDefaultService;

    @GetMapping(value = "/api/words")
    public List<Word> getAllWords() {
        return wordDefaultService.getALlWords();
    }

    @GetMapping(value = "/api/words", params = "pageNum")
    public List<Word> getWords(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        return wordDefaultService.getWordsAsPage(pageNum, 30).stream().toList();
    }


    @PostMapping("/api/words")
    public Word saveWord(Word word) {
        return wordDefaultService.saveWord(word);
    }

    @GetMapping("/api/words/{id}")
    public Word getWord(@PathVariable Long id) {
        return wordDefaultService.getWord(id);
    }

    @PatchMapping("/api/words/{id}")
    public Word updateWord(@PathVariable Long id, WordDto wordDto) {
        return wordDefaultService.updateWord(id, wordDto.getConcept(), wordDto.getMeaning());
    }

    @DeleteMapping("/api/words/{id}")
    public String deleteWord(@PathVariable Long id) {
        return wordDefaultService.deleteWord(id).toString() + " deleted.";
    }

    @GetMapping("/api/words/quiz")
    public QuizDto getQuiz() {
        return wordDefaultService.getQuiz();
    }
}
