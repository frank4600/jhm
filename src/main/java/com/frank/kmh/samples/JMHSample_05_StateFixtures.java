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
public class JMHSample_05_StateFixtures {

    double x;
    @Setup
    public void prepare() {
        x = Math.PI;
    }

    @TearDown
    public void check() {
        assert x > Math.PI : "Nothing changed?";
    }

    /*
     * This method obviously does the right thing, incrementing the field x
     * in the benchmark state. check() will never fail this way, because
     * we are always guaranteed to have at least one benchmark call.
     */

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
                .include(JMHSample_05_StateFixtures.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .threads(3)
                .warmupIterations(1).warmupTime(TimeValue.milliseconds(10))
                .measurementIterations(2).measurementTime(TimeValue.milliseconds(50))
                .build();

        new Runner(opt).run();
    }

}
