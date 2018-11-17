package com.nickolasfisher.jmhjunitsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

public class JmhJunitSampleApplicationTests {

    @Test
    public void runBenchmarks() throws Exception {
        Options options = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .mode(Mode.AverageTime)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(2)
                .threads(1)
                .measurementIterations(2)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();

        new Runner(options).run();
    }

    private static String hello = "not another hello world";

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void stringsWithoutStringBuilder() throws Exception {
        String hellos = "";
        for (int i = 0; i < 1000; i++) {
            hellos += hello;
            if (i != 999) {
                hellos += "\n";
            }
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void stringsWithStringBuilder() throws Exception {
        StringBuilder hellosBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            hellosBuilder.append(hello);
            if (i != 999) {
                hellosBuilder.append("\n");
            }
        }
    }
}
