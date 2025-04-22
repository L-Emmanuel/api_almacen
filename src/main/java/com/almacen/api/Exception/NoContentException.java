package com.almacen.api.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoContentException extends RuntimeException{

  private final String message;

    public NoContentException(String message) {
      super(message);
      this.message = message;
    }
}
