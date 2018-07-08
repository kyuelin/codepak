package dev.kyuelin.codepak.utils;

import dev.kyuelin.codepak.model.JavaClass;
import dev.kyuelin.codepak.model.JavaField;
import dev.kyuelin.codepak.model.JavaMethod;
import dev.kyuelin.codepak.model.JavaPackage;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JavaClassParser {

    private static Logger logger = Logger.getLogger(JavaClassParser.class.getName());

    public static Integer statField;
    public Integer nonStatField;
    public int nonStatPrimField;

    public JavaClassParser() {

    }

    public static JavaClass parse(final String jarClassPath, final String className) {
        JavaClass javaClass = null;
        try {
            URL[] urls = {new URL("jar:file:" + jarClassPath + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);
            Class c = cl.loadClass(className);

            javaClass = new JavaClass(className);
            for (Field f : c.getFields()) {
                javaClass.getFields().add(new JavaField(f.getName()));
            }

            for (Method m : c.getMethods()) {
                javaClass.getMethods().add(new JavaMethod(m.getName()));
            }

            javaClass.setPackageName(c.getPackage().toString());

            //Arrays.stream(c.getDeclaredFields()).forEach(System.out::println);
            Arrays.stream(c.getFields()).forEach(System.out::println);
            //Arrays.stream(c.getMethods()).forEach(System.out::println);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return javaClass;
    }

    public static void main(String args[]) {
        //JavaClassParser.parse("./target/codepak-1.0-SNAPSHOT.jar", "dev.kyuelin.codepak.utils.JavaParser");
        final String jarClassPath = "./target/codepak-1.0-SNAPSHOT.jar";
        //JarExplorer.explore(jarClassPath).stream().forEach(System.out::println);
        //JarExplorer.explore(jarClassPath).stream().map(c -> JavaClassParser.parse(jarClassPath, c)).collect(Collectors.toList()).forEach(System.out::println);
        List<String> classNames = JarExplorer.explore(jarClassPath);
        Map<String, JavaPackage> packageMap = new ConcurrentHashMap<>();
        for (String name : classNames
             ) {
            JavaClass javaClass = JavaClassParser.parse(jarClassPath, name);
            String packageName = javaClass.getPackageName();
            if (null == packageMap.get(packageName)) {
                packageMap.put(packageName, new JavaPackage(packageName));
            }
            packageMap.get(packageName).getJavaClasses().add(javaClass);
        }

        System.out.println(packageMap);
    }
}
