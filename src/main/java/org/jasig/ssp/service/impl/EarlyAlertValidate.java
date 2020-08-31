package org.jasig.ssp.service.impl;

@FunctionalInterface
public interface EarlyAlertValidate<T> {
    boolean invalid(T obj);
}
