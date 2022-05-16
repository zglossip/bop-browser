package com.zglossip.bopbrowser.benchmark.controllers;

import com.zglossip.bopbrowser.BopBrowserApplication;
import com.zglossip.bopbrowser.benchmark.AbstractBenchmark;
import com.zglossip.bopbrowser.controllers.ArtistController;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ArtistControllerBenchmark extends AbstractBenchmark {

  private static ArtistController artistController;

  @Setup
  public void setup() {
    final ConfigurableApplicationContext context = new SpringApplicationBuilder(BopBrowserApplication.class).run();
    artistController = (ArtistController) context.getBean("artistController");
  }

  @Benchmark
  public void testSearch() {
    artistController.search("Test", 0, 25);
  }
}
