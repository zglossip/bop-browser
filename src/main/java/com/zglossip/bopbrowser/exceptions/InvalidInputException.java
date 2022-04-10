package com.zglossip.bopbrowser.exceptions;

public class InvalidInputException extends RuntimeException {
  private static final long serialVersionUID = 5965124576249373083L;

  public InvalidInputException(final String s) {
    super(s);
  }

  public InvalidInputException(final String s, final Throwable cause) {
    super(s, cause);
  }
}
