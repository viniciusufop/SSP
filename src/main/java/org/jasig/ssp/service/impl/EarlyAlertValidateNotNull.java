package org.jasig.ssp.service.impl;

public class EarlyAlertValidateNotNull implements EarlyAlertValidate<Object> {
    @Override
    public boolean invalid(Object obj) {
        return obj == null;
    }
}
