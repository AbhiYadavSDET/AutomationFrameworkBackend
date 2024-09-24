package helper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PayLoadHelper {


    public static Map<String,Object> getCreateUserBody(){
        String uniqueId = UUID.randomUUID().toString();
        String bodyContent = "bar " + uniqueId;

        // Create a map of dynamic values
        Map<String, Object> replacements = new HashMap<>();

        replacements.put("body", bodyContent);
        replacements.put("userId", 2); // numeric value directly

        return replacements;
           }
    public static Map<String, Object> getUpdateUserBody() {
        String uniqueId = UUID.randomUUID().toString();
        String bodyContent = "Updated content " + uniqueId;

        // Create a map of dynamic values
        Map<String, Object> replacements = new HashMap<>();

        // Set dynamic values
        replacements.put("title", "Updated Title " + uniqueId);
        replacements.put("body", bodyContent);
        replacements.put("userId", 2); // Numeric value directly

        return replacements;
    }

}


