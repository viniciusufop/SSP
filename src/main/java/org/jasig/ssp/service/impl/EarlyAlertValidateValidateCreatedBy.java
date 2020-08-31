package org.jasig.ssp.service.impl;

import org.jasig.ssp.model.EarlyAlert;

public class EarlyAlertValidateValidateCreatedBy implements EarlyAlertValidate<EarlyAlert> {
    @Override
    public boolean invalid(EarlyAlert obj) {
        if (obj.getCreatedBy() != null && obj.getCreatedBy().getFirstName() != null) return false;
        if (obj.getCreatedBy() == null) return true;
        return false;
    }
}
