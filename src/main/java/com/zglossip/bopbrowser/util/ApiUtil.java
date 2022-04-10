package com.zglossip.bopbrowser.util;

import com.zglossip.bopbrowser.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import static com.zglossip.bopbrowser.util.BeanConstants.REST_TEMPLATE_BEAN;
import static com.zglossip.bopbrowser.util.BeanConstants.RETRY_TEMPLATE_BEAN;

@Service
public class ApiUtil {
  private final RestTemplate restTemplate;
  private final RetryTemplate retryTemplate;

  protected ApiUtil(@Qualifier(REST_TEMPLATE_BEAN) final RestTemplate restTemplate,
                    @Qualifier(RETRY_TEMPLATE_BEAN) final RetryTemplate retryTemplate) {
    this.restTemplate = restTemplate;
    this.retryTemplate = retryTemplate;
  }

  public static URI generateUri(final String uriString, final String errorMessage) {
    try {
      return new URI(uriString);
    } catch (final URISyntaxException e) {
      throw new InvalidInputException(String.format(errorMessage, e));
    }
  }

  public <T> T getRequest(final URI uri, final Class<T> clazz) {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    final HttpEntity<String> request = new HttpEntity<>(headers);
    return retryTemplate.execute(context -> restTemplate.exchange(uri, HttpMethod.GET, request, clazz).getBody());
  }
}
