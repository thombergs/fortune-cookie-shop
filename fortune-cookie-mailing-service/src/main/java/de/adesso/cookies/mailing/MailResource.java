package de.adesso.cookies.mailing;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull.List;

public class MailResource {

  @NotNull
  private String subject;

  @NotNull
  private String message;

  @NotNull
  private String recipient;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
}
