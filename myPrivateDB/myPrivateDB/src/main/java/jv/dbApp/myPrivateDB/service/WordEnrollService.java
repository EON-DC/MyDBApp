package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordEnrollService {

    private final CategoryJpaRepository categoryJpaRepository;
    private final WordJpaRepository wordJpaRepository;
    private String path = "src/main/resources/csvData.csv";
    public void enrollmentData(){
        String line = "";
        List<String> categories = categoryJpaRepository.findAll().stream()
                .map(Category::getCategorySector).toList();
        List<Word> words = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] aLine = line.split(",");
                ArrayList<String> temper = new ArrayList<>();
                String damper = "";
                /**
                 * Pseudo Code
                 * (첫번째 항이다) ? temper 저장 후 다음 반복
                 * 두번째항이고, startwith \"이다 ? 다음 " 이 나올 때까지 damper에 저장
                 * 두번째항이고, \"로 시작하지 않는다 ? 쉼표 차례대로 항을 저장하면됌
                 */
                temper.add(aLine[0]);
                if (aLine[1].startsWith("\"")) {
                    int meaningEndIndex = 0;
                    for (int i = 1; i < aLine.length; i++) {
                        if (aLine[i].endsWith("\"")) {
                            meaningEndIndex = i;
                            damper += aLine[i];
                            break;
                        }
                    }
                    temper.add(damper.substring(1, damper.length() - 1));
                    temper.add(aLine[meaningEndIndex + 1]);
                    try {
                        temper.add(aLine[meaningEndIndex + 2]);

                    } catch (IndexOutOfBoundsException e) {
                        temper.add("null");
                    }

                } else {
                    temper.add(aLine[1]);
                    temper.add(aLine[2]);
                    try {
                        temper.add(aLine[3]);

                    } catch (IndexOutOfBoundsException e) {
                        temper.add("null");
                    }
                }
                // [0] : concept, [1]: meaning, [2]: category, [3]: fullName
                Word word = new Word();
                word.setConcept(temper.get(0));
                word.setMeaning(temper.get(1));
                word.setFullName(temper.get(3));
                Category category = getCategory(temper.get(2));
                word.setCategory(category);
                words.add(word);
            }
        } catch (Exception e) {
            throw new RuntimeException("cannot read file" + e);
        }finally {
            wordJpaRepository.saveAll(words);
        }

    }

    private Category getCategory(String sector) {
        Category result = null;
        Optional<Category> category = categoryJpaRepository.findCategoryByCategorySector(sector);
        if (category.isEmpty()) {
            result = categoryJpaRepository.save(new Category(sector));
        } else {
            result = category.get();
        }
        return result;
    }

}
