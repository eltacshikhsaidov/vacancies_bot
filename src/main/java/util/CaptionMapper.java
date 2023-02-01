package util;

public class CaptionMapper {
    public static String captionMapper(String company, String salary, String vacancyTitle, String applyLink) {

        return String.format(
                """
                    Vakansiya adı: _%s_
                    Şirkət: ``` %s ```
                    Maaş: **%s**
                    Müraciət et: [link](%s)
                """,
                vacancyTitle,
                company,
                salary,
                "https://www.hellojob.az/" + applyLink
        );
    }
}
