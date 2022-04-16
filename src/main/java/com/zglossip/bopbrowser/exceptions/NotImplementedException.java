package com.zglossip.bopbrowser.exceptions;

public class NotImplementedException extends RuntimeException {

  private static final long serialVersionUID = 5102059946835815322L;

  public NotImplementedException(final String s) {
    super(s);
  }

  public NotImplementedException(final String s, final Throwable cause) {
    super(s, cause);
  }
}
