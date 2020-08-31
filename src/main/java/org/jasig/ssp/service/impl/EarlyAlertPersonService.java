package org.jasig.ssp.service.impl;

import org.jasig.ssp.model.Person;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.PersonService;

import java.util.UUID;
import java.util.function.Function;

public final class EarlyAlertPersonService {

    public static Person getPerson(final PersonService personService, final UUID id,
                                   final Function<ObjectNotFoundException, RuntimeException> execute){
        try {
            return personService.get(id);
        } catch (ObjectNotFoundException e1) {
            RuntimeException ex = execute.apply(e1);
            if(ex != null){
                throw ex;
            }
        }
        return null;
    }
}
