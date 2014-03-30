package com.urqa.library.tests;

import android.Manifest;
import android.content.pm.PackageManager;

/**
 * @author seunoh on 2014. 2. 27..
 */
public class PermissionTestCase extends UrQATestCase {


    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testInternet() throws Exception {
        String permission = Manifest.permission.INTERNET;

        if (getActivity() != null) {
            int res = getActivity().checkCallingOrSelfPermission(permission);
            assertEquals(res, PackageManager.PERMISSION_GRANTED);

        }


        assertNotNull("context is null", getActivity());
    }
}
