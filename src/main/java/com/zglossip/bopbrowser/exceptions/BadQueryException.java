package com.zglossip.bopbrowser.exceptions;

public class BadQueryException extends RuntimeException {
  private static final long serialVersionUID = -1803215850139868502L;

  public BadQueryException(final String s) {
    super(s);
  }

  public BadQueryException(final String s, final Throwable cause) {
    super(s, cause);
  }
}
