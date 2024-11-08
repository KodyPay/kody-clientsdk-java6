package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Terminal
 */
public class Terminal {
  @JsonProperty("terminalId")
  private String terminalId = null;

  @JsonProperty("online")
  private Boolean online = null;

  public Terminal terminalId(String terminalId) {
    this.terminalId = terminalId;
    return this;
  }

   /**
   * Get terminalId
   * @return terminalId
  **/
  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public Terminal online(Boolean online) {
    this.online = online;
    return this;
  }

   /**
   * Get online
   * @return online
  **/
  public Boolean isOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Terminal terminal = (Terminal) o;
    return Objects.equals(this.terminalId, terminal.terminalId) &&
        Objects.equals(this.online, terminal.online);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.terminalId == null ? 0: this.terminalId.hashCode());
    result = 31 * result + (this.online == null ? 0: this.online.hashCode());
    return result;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Terminal {\n");
    
    sb.append("    terminalId: ").append(toIndentedString(terminalId)).append("\n");
    sb.append("    online: ").append(toIndentedString(online)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

