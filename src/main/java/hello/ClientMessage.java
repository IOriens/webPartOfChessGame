package hello;

public class ClientMessage {
  private String fromTo;

  public ClientMessage() {

  }

  public ClientMessage(String fromTo) {
    this.fromTo = fromTo;
  }

  public String getFromTo() {
    return fromTo;
  }

  public void setFromTo(String fromTo) {
    this.fromTo = fromTo;
  }
}
