package com.example.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.example.common.ConvertException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by kevintian on 2017/9/6.
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> List<T> parserJsonList(InputStream instream, Class<T> clsT) {
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(
                    instream);


            JsonNode nodes = parser.readValueAsTree();

            List<T> list = new LinkedList<T>();
            for (JsonNode node : nodes) {
                list.add(objectMapper.readValue(node, clsT));
            }
            return list;
        } catch (JsonParseException e) {
            throw new ConvertException("parse json error", e);
        } catch (IOException e) {
            throw new ConvertException("parse json error", e);
        } finally {
            try {
                instream.close();
            } catch (Exception ignore) {

            }
        }
    }

    public static <T> List<T> parserJsonList(String str, Class<T> clsT) {
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);


            JsonNode nodes = parser.readValueAsTree();

            List<T> list = new LinkedList<T>();
            for (JsonNode node : nodes) {
                list.add(objectMapper.readValue(node, clsT));
            }
            return list;
        } catch (JsonParseException e) {
            throw new ConvertException("parse json error str:" + str, e);
        } catch (IOException e) {
            throw new ConvertException("parse json error str:" + str, e);
        }
    }

    public static <T> T parserJson(InputStream instream, Class<T> cls) {
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(
                    instream);
            T t = objectMapper.readValue(parser, cls);
            return t;
        } catch (JsonParseException e) {
            throw new ConvertException("parse json error", e);
        } catch (IOException e) {
            throw new ConvertException("parse json error", e);
        } finally {
            try {
                instream.close();
            } catch (Exception ignore) {

            }
        }
    }

    public static <T> T parserJson(String str, Class<T> cls) {
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(
                    str);
            T t = objectMapper.readValue(parser, cls);
            return t;
        } catch (JsonParseException e) {
            throw new ConvertException("parse json error, str:" + str, e);
        } catch (IOException e) {
            throw new ConvertException("parse json error, str:" + str, e);
        }
    }

    public static String getJsonFromObject(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            throw new ConvertException("get json error", e);
        } catch (JsonMappingException e) {
            throw new ConvertException("get json error", e);
        } catch (IOException e) {
            throw new ConvertException("get json error", e);
        }
    }

    public static <T> LinkedHashMap<String, T> parserJsonMap(String str, Class<T> clsT) {
        LinkedHashMap<String, T> map = new LinkedHashMap<String, T>();
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);

            JsonToken current;

            current = parser.nextToken();
            if (current != JsonToken.START_OBJECT) {
                throw new ConvertException("parse json error: root should be object, quiting.");
            }

            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                current = parser.nextToken();
                T obj = parser.readValueAs(clsT);
                map.put(fieldName, obj);
            }

            return map;
        } catch (JsonParseException e) {
            throw new ConvertException("parse json error str:" + str, e);
        } catch (IOException e) {
            throw new ConvertException("parse json error str:" + str, e);
        }
    }

    public static <T extends Enum<T>> EnumSet<T> parserJsonEnum(String str, Class<T> clsT) {
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(str);

            JsonNode nodes = parser.readValueAsTree();

            EnumSet<T> enumSet = EnumSet.noneOf(clsT);
            for (JsonNode node : nodes) {
                enumSet.add(objectMapper.readValue(node, clsT));
            }
            return enumSet;
        } catch (JsonParseException e) {
            throw new ConvertException("parse json error str:" + str, e);
        } catch (IOException e) {
            throw new ConvertException("parse json error str:" + str, e);
        }
    }
}
