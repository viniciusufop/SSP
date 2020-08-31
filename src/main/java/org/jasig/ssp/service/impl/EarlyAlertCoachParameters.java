package org.jasig.ssp.service.impl;

import org.jasig.ssp.model.Person;
import org.jasig.ssp.service.MessageService;
import org.jasig.ssp.service.reference.ConfigService;
import org.jasig.ssp.service.reference.MessageTemplateService;
import org.jasig.ssp.transferobject.messagetemplate.EarlyAlertMessageTemplateTO;
import org.jasig.ssp.util.DateTimeUtils;
import org.jasig.ssp.util.collections.Pair;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
//13
public class EarlyAlertCoachParameters {

    public static void createMessages(final Map<UUID, List<EarlyAlertMessageTemplateTO>> easByCoach,
                               final Date lastResponseDate,
                               final Map<UUID, Person> coaches,
                               final ConfigService configService,
                               final MessageService messageService,
                               final MessageTemplateService messageTemplateService) { //5
        Integer daysSince1900ResponseExpected = DateTimeUtils.daysSince1900(lastResponseDate); //1
        easByCoach.forEach( //1
                (coachId, earlyAlertMessageTemplateTOs) -> {
                    EarlyAlertTOComparator.sort(earlyAlertMessageTemplateTOs); //1
                    List<Pair<EarlyAlertMessageTemplateTO, Integer>> earlyAlertTOPairs =
                            earlyAlertMessageTemplateTOs
                                    .stream()
                                    .map(ea -> { //1
                                        if (ea.getLastResponseDate() != null) { //1
                                            return new Pair<>(ea, daysSince1900ResponseExpected - DateTimeUtils.daysSince1900(ea.getLastResponseDate()));
                                        }
                                        return new Pair<>(ea, daysSince1900ResponseExpected - DateTimeUtils.daysSince1900(ea.getCreatedDate()));
                                    })
                                    .filter(pair -> pair.getSecond() >= 0)//1 // Just in case attempt to only send emails for EA full day out of compliance
                                    .collect(Collectors.toList());//1
                    EarlyAlertCoachSendMessage.createMessage(coachId, earlyAlertTOPairs, coaches, configService, messageService, messageTemplateService);//1
                });
    }
}
