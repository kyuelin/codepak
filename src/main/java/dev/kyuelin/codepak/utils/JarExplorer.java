package dev.kyuelin.codepak.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarExplorer {

    private static Logger logger = Logger.getLogger(JarExplorer.class.getName());

    public JarExplorer() {
    }

    public static List<String> explore(final String jarFilePath) {
        List<String> classNames = new ArrayList<String>();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(jarFilePath));
            for (ZipEntry entry = zipInputStream.getNextEntry(); entry != null; entry = zipInputStream.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    // This ZipEntry represents a class. Now, what class does it represent?
                    String className = entry.getName().replace('/', '.'); // including ".class"
                    classNames.add(className.substring(0, className.length() - ".class".length()));
                }
            }
        } catch (IOException e) {
            logger.info(e.toString());
        }
        return classNames;
        //classNames.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        //System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath());
        JarExplorer.explore("./target/codepak-1.0-SNAPSHOT.jar").stream().forEach(System.out::println);
    }
}
