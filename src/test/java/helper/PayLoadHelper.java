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
        replacements.put("title", "foo " + uniqueId);
        replacements.put("body", bodyContent);
        replacements.put("userId", 1); // numeric value directly

        return replacements;
    }
    }


