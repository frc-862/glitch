package org.usfirst.frc862.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.usfirst.frc862.glitch.Constants;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class ConstantsBase {
    protected String getFileName() {
        return "~/config.yaml";
    }

    private String getResolvedFileName() {
        return getFileName().replaceFirst("^~", System.getProperty("user.home"));
    }

    private void withEachField(Consumer<Field> func) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!java.lang.reflect.Modifier.isFinal(field.getModifiers()))
                func.accept(field);
        }
    }

    private void withEachStaticField(Consumer<Field> func) {
        withEachField((Field f) -> {
            if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                func.accept(f);
            }
        });
    }

    public void writeToFile() {
        Logger.debug("WriteToFile: " + getResolvedFileName());
        Map<String, Object> data = new HashMap<>();
        data.put("array", new String[] { "ONE_HAND", "Stay", "away", "from", "the", "band", "saw" });
        OutputStream output = null;

        try {
            output = new FileOutputStream(getResolvedFileName());
            Writer writer = new OutputStreamWriter(output);

            withEachStaticField((Field f) -> {
                String name = f.getName();
                Object value;
                try {
                    value = f.get(this);
                    data.put(name, value);
                } catch (Exception e) {
                    System.err.println("Unable to get value for " + name);
                    e.printStackTrace();
                }
            });

            // save properties to project root folder
            Yaml yaml = new Yaml();
            yaml.dump(data, writer);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void readFromFile() {
        Yaml yaml = new Yaml(new SafeConstructor());
        InputStream input = null;

        try {
            input = new FileInputStream(getResolvedFileName());
            Object o = yaml.load(input);
            if (o instanceof Map<?, ?>) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = ((Map<String, Object>) o);

                withEachStaticField((Field f) -> {
                    try {
                        String name = f.getName();
                        Class<?> klass = f.getType();
                        Object value = map.get(name);

                        if (value != null) {
                            if (klass.equals(InterpolatedMap.class)) {
                                @SuppressWarnings("unchecked")
                                LinkedHashMap<Double, Double> tm = (LinkedHashMap<Double, Double>) value;
                                InterpolatedMap val = new InterpolatedMap();

                                for (double key : tm.keySet()) {
                                    val.put(key, tm.get(key));
                                }

                                f.set(this, val);
                            } else {
                                f.set(this, map.get(name));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (FileNotFoundException err) {
            (new Constants()).writeToFile();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
