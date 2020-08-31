package org.jasig.ssp.service.impl;

import org.jasig.ssp.dao.JournalEntryDao;
import org.jasig.ssp.dao.PersonDao;
import org.jasig.ssp.model.ObjectStatus;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.transferobject.reports.BaseStudentReportTO;
import org.jasig.ssp.transferobject.reports.JournalCaseNotesStudentReportTO;
import org.jasig.ssp.transferobject.reports.JournalStepSearchFormTO;
import org.jasig.ssp.util.sort.PagingWrapper;
import org.jasig.ssp.util.sort.SortingAndPaging;

import java.util.List;

public class JournalCaseNoteStudentReportTOsFromCriteria {

    public static List<JournalCaseNotesStudentReportTO> getJournalCaseNoteStudentReportTOsFromCriteria(JournalStepSearchFormTO personSearchForm, SortingAndPaging sAndP, JournalEntryDao dao, PersonDao personDao)
            throws ObjectNotFoundException { //5
        final List<JournalCaseNotesStudentReportTO> personsWithJournalEntries = dao.getJournalCaseNoteStudentReportTOsFromCriteria(personSearchForm, sAndP); //1
        final SortingAndPaging personSAndP = SortingAndPaging.createForSingleSortAll(ObjectStatus.ACTIVE, "lastName", "DESC") ; //1
        final PagingWrapper<BaseStudentReportTO> persons = personDao.getStudentReportTOs(personSearchForm, personSAndP); //2

        if (persons == null) { //1
            return personsWithJournalEntries;
        }

        JournalEntryProcess.addPersonWithJournalEntries(personSearchForm, personsWithJournalEntries, persons, dao); //1

        JournalCaseNotesStudentReportTOComparator.sortByStudentName(personsWithJournalEntries);//1

        return personsWithJournalEntries;
    }

}
