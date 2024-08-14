package Utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PayloadLoader {


    public static String loadPayload(String fileName, Map<String, Object> replacements) throws IOException {
        String filePath = "src/test/resources/payloads/" + fileName;
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        for (Map.Entry<String, Object> entry : replacements.entrySet()) {

            // Replace placeholder with the actual value
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());
        }

        return content;
    }
}
