package org.jasig.ssp.service.impl;

import org.jasig.ssp.transferobject.reports.JournalCaseNotesStudentReportTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//1
public class JournalCaseNotesStudentReportTOComparator implements Comparator<JournalCaseNotesStudentReportTO> {

    @Override //1
    public int compare(JournalCaseNotesStudentReportTO p1, JournalCaseNotesStudentReportTO p2) { //1
        int value = p1.getLastName().compareToIgnoreCase(p2.getLastName());
        if(value != 0) return value; //1

        value = p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
        if(value != 0) //1
            return value;
        if(p1.getMiddleName() == null && p2.getMiddleName() == null) //1
            return 0;
        if(p1.getMiddleName() == null) //1
            return -1;
        if(p2.getMiddleName() == null) //1
            return 1;
        return p1.getMiddleName().compareToIgnoreCase(p2.getMiddleName());
    }

    //1
    static void sortByStudentName(List<JournalCaseNotesStudentReportTO> toSort) {
        Collections.sort(toSort,  new JournalCaseNotesStudentReportTOComparator()); //1
    }
}
