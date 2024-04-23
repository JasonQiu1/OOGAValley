package oogasalad.model.data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class DataValidation {

    private static Map<String, List<String>> types;
    public static void validateProperties(Properties properties){
`
        if (properties == null) {
            throw new IllegalArgumentException("Properties cannot be null");
        }

        types = properties.getCopyOfPropertyTypes();

        // Get the class of the Properties object
        Class<?> propertiesClass = properties.getClass();

        // Get all declared fields of the Properties class
        Field[] fields = propertiesClass.getDeclaredFields();

        // Iterate over the fields and print their names
        for (Field field : fields) {
            // Set accessible to true to access private fields
            field.setAccessible(true);
            // Get the value of the field for the properties object
            Object value = null;
            try {
                value = field.get(properties);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if(value != null){
              //  validateProperty(value);
            }


            // Print the name and value of the field
            System.out.println("Field: " + field.getName() + ", Value: " + value);
        }
    }

    private static void validateProperty(Map<?,?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            // If the value is a map, recursively process it
            if (value instanceof Map) {
                validateProperty((Map<?, ?>) value);
            } else if (value instanceof List) {
                // If the value is a list, iterate over it and process each element
                for (Object listItem : (List<?>) value) {
                    if (listItem instanceof Map) {
                        validateProperty((Map<?, ?>) listItem);
                    } else {
                        // Process the value if it's not a map
                        validate(key, value);
                    }
                }
            } else {
                // Process the value if it's not a map
                validate(key, value);
            }
        }
    }

    private static void validate(Object key, Object value) {
        for(Map.Entry<String, List<String>> type : types.entrySet()){
            if(type.getValue().contains(String.valueOf(key))){
                String val = (String)value;
                switch(type.getKey()){
                    case "int" -> {
                        try {
                            Integer.parseInt(val);
                        } catch (NumberFormatException e) {
                            System.out.println("The string cannot be cast as an int: " + e.getMessage());
                        }
                    }
                    case "boolean" -> {
                        if(!(val.equals("true"))){
                            System.out.println("The string cannot be cast as a boolean: " + val);
                        }
                    }
                    case "double" -> {
                        try {
                            Double.parseDouble(val);
                        } catch (NumberFormatException e) {
                            System.out.println("The string cannot be cast as an double: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }



}
