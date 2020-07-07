package org.smartregister.chw.core.shadows;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.smartregister.location.helper.LocationHelper;

@Implements(LocationHelper.class)
public class LocationHelperShadowHelper {

    @Implementation
    public String getOpenMrsLocationId(String locationName) {
        return "test_location_id";
    }
}
