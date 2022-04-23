package com.zglossip.bopbrowser.benchmark.controllers;

import com.zglossip.bopbrowser.benchmark.AbstractBenchmark;
import com.zglossip.bopbrowser.controllers.ArtistController;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@RunWith(SpringRunner.class)
public class ArtistControllerBenchmark extends AbstractBenchmark {

  private static ArtistController artistController;

  @Autowired
  void setArtistService(final ArtistController artistService) {
    ArtistControllerBenchmark.artistController = artistController;
  }

  @Benchmark
  public void testSearch() {
    artistController.search("Test");
  }
}
