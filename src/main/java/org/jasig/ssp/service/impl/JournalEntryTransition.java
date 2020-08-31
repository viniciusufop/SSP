package org.jasig.ssp.service.impl;

import org.jasig.ssp.model.JournalEntry;
import org.jasig.ssp.model.JournalEntryDetail;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.PersonProgramStatusService;
import org.jasig.ssp.web.api.validation.ValidationException;

//6
public class JournalEntryTransition {

    public static void checkForTransition(final JournalEntry journalEntry, final PersonProgramStatusService personProgramStatusService)
            throws ObjectNotFoundException, ValidationException { //4
        // search for a JournalStep that indicates a transition
        for (final JournalEntryDetail detail : journalEntry.getJournalEntryDetails()) { //1
            if (detail.getJournalStepJournalStepDetail().getJournalStep().isUsedForTransition()) { //1
                // is used for transition, so attempt to set program status
                personProgramStatusService.setTransitionForStudent(journalEntry.getPerson());
                // exit early because no need to loop through others
                return;
            }
        }
    }
}
