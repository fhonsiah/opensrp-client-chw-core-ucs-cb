package org.smartregister.chw.core.rule;

import android.content.Context;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.util.ReflectionHelpers;
import org.smartregister.chw.core.BaseRobolectricTest;
import org.smartregister.chw.core.implementation.MonthlyAlertRuleImpl;

import java.util.Date;

public class MonthlyAlertRuleTest extends BaseRobolectricTest {

    private MonthlyAlertRule monthlyAlertRule;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        monthlyAlertRule = new MonthlyAlertRuleImpl(context, new Date().getTime(), new Date().getTime());
    }

    @Test
    public void lastDueDateIs1stIfLastVisitEarlierThanCreated() throws Exception {
        Date firstDayOfThisMonth = LocalDate.now().withDayOfMonth(1).toDate();
        LocalDate dateCreated = LocalDate.fromDateFields(firstDayOfThisMonth).plusWeeks(2);
        LocalDate lastVisitDate = LocalDate.fromDateFields(firstDayOfThisMonth).plusWeeks(1);

        ReflectionHelpers.setField(monthlyAlertRule, "dateCreated", dateCreated);
        ReflectionHelpers.setField(monthlyAlertRule, "lastVisitDate", lastVisitDate);

        Assert.assertEquals(firstDayOfThisMonth, Whitebox.invokeMethod(monthlyAlertRule, "getLastDueDate"));
    }
}
