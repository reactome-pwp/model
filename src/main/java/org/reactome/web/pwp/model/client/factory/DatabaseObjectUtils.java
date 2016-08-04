package org.reactome.web.pwp.model.client.factory;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import org.reactome.web.pwp.model.client.classes.DatabaseObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class DatabaseObjectUtils {

    public static Boolean getBooleanValue(JSONObject jsonObject, String key) {
        JSONValue aux = jsonObject.get(key);
        if (aux != null) {
            if (aux.isBoolean() != null) {
                return aux.isBoolean().booleanValue();
            }
            if (aux.isString() != null) {
                return Boolean.valueOf(aux.isString().stringValue());
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends DatabaseObject> T getDatabaseObject(JSONObject jsonObject, String key) {
        JSONValue aux = jsonObject.get(key);
        if (aux != null) {
            if (aux.isObject() != null) {
                return (T) DatabaseObjectFactory.create(aux.isObject());
            }
            if (aux.isNumber() != null) {
                //It doesn't work if the object with content hasn't been parsed before the reference appear
                Long dbId = (long) aux.isNumber().doubleValue();
                return (T) DatabaseObjectFactory.cache.get(dbId + "");
            }
        }
        return null;
    }

    public static Integer getIntValue(JSONObject jsonObject, String key) {
        JSONValue aux = jsonObject.get(key);
        if (aux != null) {
            if (aux.isNumber() != null) {
                return (int) aux.isNumber().doubleValue();
            }
            if (aux.isString() != null) {
                return Integer.valueOf(aux.isString().stringValue());
            }
        }
        return null;
    }

    public static Long getLongValue(JSONObject jsonObject, String key) {
        JSONValue aux = jsonObject.get(key);
        if (aux != null) {
            if (aux.isNumber() != null) {
                return (long) aux.isNumber().doubleValue();
            }
            if (aux.isString() != null) {
                return Long.valueOf(aux.isString().stringValue());
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends DatabaseObject> List<T> getObjectList(JSONObject jsonObject, String key) {
        List<T> list = new LinkedList<>();
        JSONValue aux = jsonObject.get(key);
        if (aux != null) {
            JSONArray listAux = aux.isArray();
            if (listAux != null) {
                for (int i = 0; i < listAux.size(); ++i) {
                    JSONValue value = listAux.get(i);
                    // The reason why DatabaseObjectFactory.cmds is used in both (if and else) is
                    // to preserve the order in which the elements are returned from the server
                    if (value.isObject() != null) {
                        T obj = (T) DatabaseObjectFactory.create(value.isObject());
                        DatabaseObjectFactory.cmds.add(() -> list.add(obj));
                    } else if (value.isNumber() != null) {
                        Long dbId = (long) value.isNumber().doubleValue();
                        DatabaseObjectFactory.cmds.add(() -> {
                            T obj = (T) DatabaseObjectFactory.cache.get(dbId + "");
                            if (obj != null) list.add(obj);
                        });
                    }
                }
            } else if (aux.isObject() != null){
                list.add((T) DatabaseObjectFactory.create(aux.isObject()));
            } else if (aux.isNumber() != null) {
                String dbId = (long) aux.isNumber().doubleValue() + "";
                DatabaseObjectFactory.cmds.add(() -> {
                    T obj = (T) DatabaseObjectFactory.cache.get(dbId);
                    if (obj != null) list.add(obj);
                });
            }
        }
        return list;
    }

    public static SchemaClass getSchemaClass(JSONObject jsonObject) {
        JSONValue aux = jsonObject.get("schemaClass");
        JSONString val = null;
        if (aux != null) {
            val = aux.isString();
        } else {
            aux = jsonObject.get("className");
            if (aux != null) {
                val = aux.isString();
            }
        }
        return SchemaClass.getSchemaClass((val != null) ? val.stringValue() : null);
    }

    public static List<String> getStringList(JSONObject jsonObject, String key) {
        List<String> list = new LinkedList<>();
        JSONValue aux = jsonObject.get(key);
        if (aux != null) {
            JSONArray listAux = aux.isArray();
            if (listAux != null) {
                for (int i = 0; i < listAux.size(); ++i) {
                    list.add(listAux.get(i).isString().stringValue());
                }
            } else if (aux.isString() != null) {
                list.add(aux.isString().stringValue());
            }
        }
        return list;
    }

    public static String getStringValue(JSONObject jsonObject, String key) {
        JSONValue aux = jsonObject.get(key);
        if (aux != null && aux.isString() != null) {
            return aux.isString().stringValue();
        }
        return null;
    }
}
