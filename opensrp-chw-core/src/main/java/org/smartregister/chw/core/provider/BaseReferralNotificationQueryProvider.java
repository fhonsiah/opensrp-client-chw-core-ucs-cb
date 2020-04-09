package org.smartregister.chw.core.provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.smartregister.chw.core.utils.CoreReferralUtils;

public class BaseReferralNotificationQueryProvider {
    /**
     * Return query to be used to select object_ids from the search table so that these objects_ids
     * are later used to retrieve the actual rows from the normal(non-FTS) table
     *
     * @param filters This is the search phrase entered in the search box
     * @return query string for getting object ids
     */
    @NonNull
    public String getObjectIdsQuery(@Nullable String filters) {
        return CoreReferralUtils.getFamilyMemberFtsSearchQuery(filters);
    }

    /**
     * Return query(s) to be used to perform the total count of register clients eg. If OPD combines records
     * in multiple tables then you can provide multiple queries with the result having a single row+column
     * and the counts will be summed up. Kindly try to use the search tables wherever possible.
     *
     * @return query string used for counting items
     */
    @NonNull
    public String[] countExecuteQueries() {
        return new String[]{
            "/* COUNT NOTIFICATION REFERRALS MARKED AS DONE AT THE FACILITY */\n" +
                    "SELECT COUNT(*) AS c\n" +
                    "FROM task\n" +
                    "         inner join ec_family_member on ec_family_member.base_entity_id = task.for\n" +
                    "         inner join ec_close_referral on ec_close_referral.referral_task = task._id\n" +
                    "         inner join event on ec_close_referral.id = event.formSubmissionId\n" +
                    "\n" +
                    "WHERE ec_family_member.is_closed = '0'\n" +
                    "  AND ec_family_member.date_removed is null\n" +
                    "  AND task.business_status = 'Complete'\n" +
                    "  AND task.status = 'COMPLETED'\n" +
                    "  AND task.code = 'Referral'\n"
        };
    }

    /**
     * Return query to be used to retrieve the client details. This query should have a "WHERE base_entity_id IN (%s)" clause where
     * the comma-separated  base-entity-ids for the clients will be inserted into the query and later
     * executed
     *
     * @return query string used for retrieving client details
     */
    @NonNull
    public String mainSelectWhereIDsIn() {
      return  "/* NOTIFICATION FROM REFERRALS MARKED AS DONE AT THE FACILITY */\n" +
              "SELECT ec_family_member.first_name    AS first_name,\n" +
              "       ec_family_member.middle_name   AS middle_name,\n" +
              "       ec_family_member.last_name     AS last_name,\n" +
              "       ec_family_member.dob           AS dob,\n" +
              "       ec_family_member.id            AS _id,\n" +
              "       task._id                       AS  referral_task_id,\n" +
              "       ec_family_member.relational_id AS relationalid,\n" +
              "       event.dateCreated              AS notification_date,\n" +
              "       'Successful referral'          AS notification_type\n" +
              "FROM task\n" +
              "         inner join ec_family_member on ec_family_member.base_entity_id = task.for\n" +
              "         inner join ec_close_referral on ec_close_referral.referral_task = task._id\n" +
              "         inner join event on ec_close_referral.id = event.formSubmissionId\n" +
              "\n" +
              "WHERE ec_family_member.is_closed = '0'\n" +
              "  AND ec_family_member.date_removed is null\n" +
              "  AND task.business_status = 'Complete'\n" +
              "  AND task.status = 'COMPLETED'\n" +
              "  AND task.code = 'Referral'\n" +
              "  AND ec_family_member.base_entity_id IN (%s)\n" +
              "ORDER BY event.dateCreated DESC\n" +
              "\n";
    }
}
