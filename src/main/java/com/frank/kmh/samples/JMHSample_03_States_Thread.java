package com.frank.kmh.samples;

/**
 * @author ZhengGuoJun
 */

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

public class JMHSample_03_States_Thread {
    @State(Scope.Thread)
    public static class ThreadState {
        volatile int x = 0;
    }
    @Benchmark
    public void measureUnshared(ThreadState state) throws Exception{
        state.x++;
        TimeUnit.MILLISECONDS.sleep(800);
        System.out.println(state.x);
    }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_03_States_Thread.class.getSimpleName())
                .threads(3)
                .forks(1)
                .warmupIterations(1).warmupTime(TimeValue.milliseconds(10))
                .measurementIterations(2).measurementTime(TimeValue.seconds(3))
                .build();
        new Runner(opt).run();
    }
}