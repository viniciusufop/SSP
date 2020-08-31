package org.jasig.ssp.service.impl;

import org.jasig.ssp.transferobject.EarlyAlertTO;
import org.jasig.ssp.transferobject.messagetemplate.EarlyAlertMessageTemplateTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EarlyAlertTOComparator implements Comparator<EarlyAlertTO> {
    @Override
    public int compare(EarlyAlertTO p1, EarlyAlertTO p2) {
        Date p1Date = p1.getLastResponseDate();
        if (p1Date == null)
            p1Date = p1.getCreatedDate();
        Date p2Date = p2.getLastResponseDate();
        if (p2Date == null)
            p2Date = p2.getCreatedDate();
        return p1Date.compareTo(p2Date);
    }


    static void sort(List<EarlyAlertMessageTemplateTO> earlyAlertMessageTemplateTOS){
        Collections.sort(earlyAlertMessageTemplateTOS, new EarlyAlertTOComparator());
    }
}
