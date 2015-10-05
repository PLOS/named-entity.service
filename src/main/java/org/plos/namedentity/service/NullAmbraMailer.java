package org.plos.namedentity.service;

import org.ambraproject.service.mailer.AmbraMailer;

import java.util.Map;

/**
 * This class is a hack to allow us to temporarially use the userregistrationservice
 * while preventing it from sending emails.
 */
public class NullAmbraMailer implements AmbraMailer {

  public void setEmailThisArticleMap(final Map<String, String> emailThisArticleMap) {
  }

  public void setFeedbackEmailMap(final Map<String, String> feedbackEmailMap) {
  }

  public void setAutoIngestEmailMap(final Map<String, String> autoIngestEmailMap) {
  }

  public void setErrorEmailMap(final Map<String, String> errorEmailMap) {
  }

  public void setRegistrationEmailMap(Map<String, String> registrationEmailMap) {
  }

  public void setForgotPasswordEmailMap(Map<String, String> forgotPasswordEmailMap) {
  }

  public void setChangeEmailMap(Map<String, String> changeEmailMap) {
  }

  public void sendEmailThisArticleEmail(final String toEmailAddress, final String fromEmailAddress,
                                        final Map<String, String> mapFields) {
  }

  public void sendFeedback(final String fromEmailAddress, final Map<String, Object> mapFields) {
  }

  public void sendIngestNotify(final Map<String, Object> mapFields) {
  }

  public void sendError(String message) {
  }

  @Override
  public void sendVerificationEmail(String email, String verificationToken) {
  }

  @Override
  public void sendForgotPasswordEmail(String email, String verificationToken) {
  }

  @Override
  public void sendChangeEmailNotice(String oldEmail, String newEmail, String verificationToken) {
  }
}
