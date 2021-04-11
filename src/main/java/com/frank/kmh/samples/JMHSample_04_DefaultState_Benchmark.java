package com.frank.kmh.samples;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhengGuoJun
 */

//@State(Scope.Thread)
@State(Scope.Benchmark)
public class JMHSample_04_DefaultState_Benchmark {

    AtomicInteger x = new AtomicInteger(1);

    @Benchmark
    public void measure() throws Exception{
        TimeUnit.MILLISECONDS.sleep(800);
        System.out.println(x.addAndGet(1));

    }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_04_DefaultState_Benchmark.class.getSimpleName())
                .forks(1)
                .threads(3)
                .warmupIterations(1).warmupTime(TimeValue.milliseconds(10))
                .measurementIterations(2).measurementTime(TimeValue.milliseconds(50))
                .build();

        new Runner(opt).run();
    }

}