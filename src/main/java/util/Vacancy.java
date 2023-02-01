package util;

import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Data
@Builder
public class Vacancy {
    private InputFile img;
    private String caption;
    private String applyLink;
}
