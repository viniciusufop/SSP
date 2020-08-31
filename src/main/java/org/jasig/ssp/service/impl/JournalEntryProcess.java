package org.jasig.ssp.service.impl;

import org.apache.commons.lang.StringUtils;
import org.jasig.ssp.dao.JournalEntryDao;
import org.jasig.ssp.transferobject.reports.BaseStudentReportTO;
import org.jasig.ssp.transferobject.reports.JournalCaseNotesStudentReportTO;
import org.jasig.ssp.transferobject.reports.JournalStepSearchFormTO;
import org.jasig.ssp.util.sort.PagingWrapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JournalEntryProcess {

    //1
    public static void addPersonWithJournalEntries(final JournalStepSearchFormTO personSearchForm, final List<JournalCaseNotesStudentReportTO> personsWithJournalEntries, final PagingWrapper<BaseStudentReportTO> persons, JournalEntryDao dao) {
        final Map<String, JournalCaseNotesStudentReportTO> map = personsWithJournalEntries
                .stream()
                .collect(Collectors.toMap(BaseStudentReportTO::getSchoolId, j -> j)); //1 TODO map é variavel de controle o que importa é o personsWithJournalEntries
        for (BaseStudentReportTO person:persons) { //1
            if (!map.containsKey(person.getSchoolId())  && StringUtils.isNotBlank(person.getCoachSchoolId())) { //2
                if(personSearchForm.getJournalSourceIds() == null || dao.getJournalCountForPersonForJournalSourceIds(person.getId(), personSearchForm.getJournalSourceIds()) != 0){ //1
                    final JournalCaseNotesStudentReportTO entry = new JournalCaseNotesStudentReportTO(person);
                    personsWithJournalEntries.add(entry);
                    map.put(entry.getSchoolId(), entry);
                }
            }
        }
    }
}
