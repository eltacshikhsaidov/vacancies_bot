package util;

import lombok.SneakyThrows;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class Svg2Png {

    private static final String DEFAULT_VACANCY_IMAGE_URL = "https://www.hellojob.az/uploads/workIcons/computer.svg";

    @SneakyThrows
    public static byte[] svg2png(String imageUrl) {

        if (!imageUrl.endsWith(".svg")) {
//            System.out.println("Invalid image url provided: url='" + imageUrl + "}'\n Returning default image");

            return svg2png(DEFAULT_VACANCY_IMAGE_URL);
        }

        System.out.println("Getting SVG image from url='{" + imageUrl + "}'");
        URL url = new URL(imageUrl);
        TranscoderInput input = new TranscoderInput(url.openStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(outputStream);
        PNGTranscoder transcoder = new PNGTranscoder();

//        System.out.println("Converting SVG to PNG started");
        transcoder.transcode(input, output);
//        System.out.println("Converted successfully for imageUrl='{" + imageUrl + "}'");
        return outputStream.toByteArray();
    }
}
