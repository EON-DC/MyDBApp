package jv.dbApp.myPrivateDB.rest;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.service.WordDefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WordRestController {

    private final WordDefaultService wordDefaultService;

    @GetMapping("/")
    public List<Word> getAllWords() {
        return wordDefaultService.getALlWords();
    }

}
