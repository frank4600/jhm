package com.frank.kmh.samples;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * @author ZhengGuoJun
 */
@State(Scope.Thread)
public class JMHSample_06_FixtureLevel {

    double x;

    @TearDown(Level.Iteration)
    public void check() {
        assert x > Math.PI : "Nothing changed?";
    }

    @Benchmark
    public void measureRight() {
        x++;
    }

    @Benchmark
    public void measureWrong() {
        double x = 0;
        x++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_06_FixtureLevel.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                // switch to "true" to fail the complete run
                .shouldFailOnError(false)
                .warmupIterations(1).warmupTime(TimeValue.milliseconds(10))
                .measurementIterations(2).measurementTime(TimeValue.milliseconds(50))
                .build();

        new Runner(opt).run();
    }

}
