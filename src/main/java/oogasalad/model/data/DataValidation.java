package oogasalad.model.data;

import oogasalad.model.api.exception.BadValueParseException;
import oogasalad.model.api.exception.InvalidRuleType;

import java.util.List;
import java.util.Map;

public class DataValidation {

    private static Map<String, List<String>> types;


    public static void validateProperties(Properties properties) throws InvalidRuleType{
        types = properties.getCopyOfPropertyTypes();
            for (Map.Entry<String, String> entry : properties.getCopyOfProperties().entrySet()) {
                validate(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, List<String>> entry : properties.getCopyOfListProperties().entrySet()) {
                for (String str : entry.getValue()) {
                    validate(entry.getKey(), str);
                }
            }
            for (Map<String, String> entryOut : properties.getCopyOfMapProperties().values()) {
                for (Map.Entry<String, String> entryIn : entryOut.entrySet()) {
                    validate(entryIn.getKey(), entryIn.getValue());
                }
            }
    }

    public static void validate(String key, String value) throws InvalidRuleType{
        for (Map.Entry<String, List<String>> type : types.entrySet()) {
            if (type.getValue().contains(key)) {
                try {
                    switch (type.getKey()) {
                        case "int":
                            validateInteger(value);
                            break;
                        case "boolean":
                            validateBoolean(value);
                            break;
                        case "double":
                            validateDouble(value);
                            break;
                    }
                } catch (BadValueParseException e){
                    throw new InvalidRuleType(key, value, e.getParseType());
                }
            }
        }
    }

    private static void validateInteger(String value) throws BadValueParseException {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new BadValueParseException(value, "int");
        }
    }

    private static void validateBoolean(String value) throws BadValueParseException {
        if (!(value.equals("true") || value.equals("false"))) {
            throw new BadValueParseException(value, "boolean");
        }
    }

    private static void validateDouble(String value) throws BadValueParseException {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new BadValueParseException(value, "double");
        }
    }


    //        if (properties == null) {
//            throw new IllegalArgumentException("Properties cannot be null");
//        }
//
//        types = properties.getCopyOfPropertyTypes();
//
//        // Get the class of the Properties object
//        Class<?> propertiesClass = properties.getClass();
//
//        // Get all declared fields of the Properties class
//        Field[] fields = propertiesClass.getDeclaredFields();
//
//        // Iterate over the fields and print their names
//        for (Field field : fields) {
//            // Set accessible to true to access private fields
//            field.setAccessible(true);
//            // Get the value of the field for the properties object
//            Object value = null;
//            try {
//                value = field.get(properties);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//            if(value != null){
//              //  validateProperty(value);
//            }
//
//
//            // Print the name and value of the field
//            System.out.println("Field: " + field.getName() + ", Value: " + value);
//        }
//    }
//
//    private static void validateProperty(Map<?,?> map) {
//        for (Map.Entry<?, ?> entry : map.entrySet()) {
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//
//            // If the value is a map, recursively process it
//            if (value instanceof Map) {
//                validateProperty((Map<?, ?>) value);
//            } else if (value instanceof List) {
//                // If the value is a list, iterate over it and process each element
//                for (Object listItem : (List<?>) value) {
//                    if (listItem instanceof Map) {
//                        validateProperty((Map<?, ?>) listItem);
//                    } else {
//                        // Process the value if it's not a map
//                        validate(key, value);
//                    }
//                }
//            } else {
//                // Process the value if it's not a map
//                validate(key, value);
//            }
//        }
//    }
//

}
