package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * TerminalsResponse
 */
public class TerminalsResponse {
  @JsonProperty("terminals")
  private List<Terminal> terminals = null;

  public TerminalsResponse terminals(List<Terminal> terminals) {
    this.terminals = terminals;
    return this;
  }

  public TerminalsResponse addTerminalsItem(Terminal terminalsItem) {
    if (this.terminals == null) {
      this.terminals = new ArrayList<Terminal>();
    }
    this.terminals.add(terminalsItem);
    return this;
  }

   /**
   * Get terminals
   * @return terminals
  **/
  public List<Terminal> getTerminals() {
    return terminals;
  }

  public void setTerminals(List<Terminal> terminals) {
    this.terminals = terminals;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminalsResponse terminalsResponse = (TerminalsResponse) o;
    return Objects.equals(this.terminals, terminalsResponse.terminals);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.terminals == null ? 0: this.terminals.hashCode());
    return result;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminalsResponse {\n");
    
    sb.append("    terminals: ").append(toIndentedString(terminals)).append("\n");
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

