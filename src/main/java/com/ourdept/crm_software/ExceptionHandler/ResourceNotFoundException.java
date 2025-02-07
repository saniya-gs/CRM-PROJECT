package com.ourdept.crm_software.ExceptionHandler;

import java.io.Serializable;

import com.ourdept.crm_software.crm.utils.HttpStatusCodes;

public class ResourceNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_STATUS_CODE = HttpStatusCodes.CONFLICT;


    public ResourceNotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return DEFAULT_STATUS_CODE;
    }
}
