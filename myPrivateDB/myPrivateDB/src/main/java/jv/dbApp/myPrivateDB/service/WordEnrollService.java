package jv.dbApp.myPrivateDB.service;

import jv.dbApp.myPrivateDB.domain.Category;
import jv.dbApp.myPrivateDB.domain.Word;
import jv.dbApp.myPrivateDB.repository.CategoryJpaRepository;
import jv.dbApp.myPrivateDB.repository.WordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] aLine = line.split(",");
                ArrayList<String> temper = new ArrayList<>();
                String damper = "";
                Small:
                for (String sector : aLine) {
                    if (sector.startsWith("\"")) {
                        damper += sector;
                        continue Small;
                    }
                    if (sector.endsWith("\"")) {
                        damper += sector;
                        temper.add(damper.substring(1, damper.length() - 1));
                        damper = "";
                        continue Small;
                    }
                    temper.add(sector);
                }
                if (temper.size() == 3) {
                    temper.add("null");
                }

                System.out.println("temper = " + temper.toString());
                // [0] : concept, [1]: meaning, [2]: category, [3]: fullName
                Word word = new Word();
                word.setConcept(temper.get(0));
                word.setMeaning(temper.get(1));
                word.setFullName(temper.get(3));
                Category category = getCategory(temper.get(2));
                word.setCategory(category);
                wordJpaRepository.save(word);
            }
        } catch (Exception e) {
            throw new RuntimeException("cannot read file" + e);
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
