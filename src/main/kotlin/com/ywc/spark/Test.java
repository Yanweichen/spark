package com.ywc.spark;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author yanweichen
 * @date 2018/6/20
 */
public class Test {

    public static void main(String[] args) throws IOException {
//        String s = IOUtils.toString(URI.create("http://www.celestrak.com/NORAD/elements/tle-new.txt"), "UTF-8");
//        String[] split = s.split(System.lineSeparator());
//        for (String s1 : split) {
//            System.out.println(s1);
//        }
        File file = new File("C:\\Users\\yanweichen\\Desktop\\new_data.txt");
        FileUtils.readLines(new File("C:\\Users\\yanweichen\\Desktop\\新建文件夹\\OrbProp\\result_position_lla.txt")
                , "UTF-8").stream()
                .map(s -> {
                    List<String> collect = Arrays.stream(s.split(" ")).filter(s1 -> !s1.equals("")).collect(toList());
                    collect.remove(0);
                    collect.remove(0);
                    return collect;
                }).forEach(l -> {
                    try {
                        FileUtils.write(file
                                , l.get(0) + "," + l.get(1) + "," + l.get(2) + ","
                                , "UTF-8", true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
//        String a = "a   b   c";
//        System.out.println(a.split(" "));
//        boolean b = Stream.of(a.split(" ")).anyMatch(s -> s.equals(" "));
//        System.out.println(b);

    }
}
