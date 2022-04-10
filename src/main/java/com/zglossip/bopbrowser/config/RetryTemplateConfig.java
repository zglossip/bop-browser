package com.zglossip.bopbrowser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import static com.zglossip.bopbrowser.util.BeanConstants.REST_TEMPLATE_BEAN;
import static com.zglossip.bopbrowser.util.BeanConstants.RETRY_TEMPLATE_BEAN;

@Configuration
@Profile("!integration")
public class RetryTemplateConfig {

  private static final Integer MAX_ATTEMPTS = 5;
  private static final Integer RETRY_TIME_RETRIEVAL = 500;

  @Bean(REST_TEMPLATE_BEAN)
  public static RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean(RETRY_TEMPLATE_BEAN)
  public RetryTemplate getRetryTemplate() {
    final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(MAX_ATTEMPTS);

    final FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    backOffPolicy.setBackOffPeriod(RETRY_TIME_RETRIEVAL);

    final RetryTemplate template = new RetryTemplate();
    template.setRetryPolicy(retryPolicy);
    template.setBackOffPolicy(backOffPolicy);

    return template;
  }
}
