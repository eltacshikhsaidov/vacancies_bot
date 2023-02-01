package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.VacancyElement.vacancyInfo;

public class AllVacancies {
    public static List<Vacancy> vacancies() {
        String websiteUrl = "https://www.hellojob.az/";

        List<Vacancy> vacancies = new ArrayList<>();
        try {

            Document document = Jsoup.connect(websiteUrl).get();
            Elements elements = document.select(".card_name_adn_img");

            for (Element element : elements) {
                vacancies.add(vacancyInfo(element));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }
}
