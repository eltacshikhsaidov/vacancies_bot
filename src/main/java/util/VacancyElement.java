package util;

import org.jsoup.nodes.Element;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static util.CaptionMapper.captionMapper;

public class VacancyElement {
    public static Vacancy vacancyInfo(Element element) {
        String imgUrl = element.select(".card_img>img").attr("src");

        String salary;
        String company;
        String vacancyTitle;
        String applyLink = requireNonNull(element.parent()).attr("href");

        byte[] pngImageBytes = Svg2Png.svg2png(imgUrl);

        InputFile img = new InputFile(
                new ByteArrayInputStream(pngImageBytes),
                "vacancy" + UUID.randomUUID() + ".png"
        );

        Element vacancyBody = element.select(".card_name").first();

        vacancyTitle = requireNonNull(
                requireNonNull(vacancyBody).
                        getElementsByTag("h3").first()).text();

        company = requireNonNull(vacancyBody
                .selectFirst(".company_name")).text();

        salary = requireNonNull(vacancyBody.selectFirst(".salary")).text();

        return Vacancy
                .builder()
                .img(img)
                .caption(
                        captionMapper(
                                company,
                                salary,
                                vacancyTitle,
                                applyLink
                        )
                )
                .build();
    }
}
