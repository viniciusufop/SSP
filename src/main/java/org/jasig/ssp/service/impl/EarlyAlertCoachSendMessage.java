package org.jasig.ssp.service.impl;

import org.jasig.ssp.model.Person;
import org.jasig.ssp.model.SubjectAndBody;
import org.jasig.ssp.service.MessageService;
import org.jasig.ssp.service.reference.ConfigService;
import org.jasig.ssp.service.reference.MessageTemplateService;
import org.jasig.ssp.transferobject.messagetemplate.EarlyAlertMessageTemplateTO;
import org.jasig.ssp.util.DateTimeUtils;
import org.jasig.ssp.util.collections.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//8
public class EarlyAlertCoachSendMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(EarlyAlertCoachSendMessage.class);
    public static void createMessage(final UUID coachId,
                                     final List<Pair<EarlyAlertMessageTemplateTO, Integer>> earlyAlertTOPairs,
                                     final Map<UUID, Person> coaches,
                                     final ConfigService configService,
                                     final MessageService messageService,
                                     final MessageTemplateService messageTemplateService){ // 5
        Person person = coaches.get(coachId);
        Map<String, Object> messageParams = new HashMap<>();
        messageParams.put("earlyAlertTOPairs", earlyAlertTOPairs);
        messageParams.put("coach", person);
        messageParams.put("DateTimeUtils", DateTimeUtils.class);
        messageParams.put("termToRepresentEarlyAlert", configService.getByNameEmpty("term_to_represent_early_alert"));

        SubjectAndBody subjAndBody = messageTemplateService.createEarlyAlertResponseRequiredToCoachMessage(messageParams); //1
        try { //1
            messageService.createMessage(person, null, subjAndBody);
        } catch (Exception exp) { //1
            LOGGER.error("Unable to send reminder emails to coach: " + person.getFullName() + "\n", exp);
        }
    }
}
