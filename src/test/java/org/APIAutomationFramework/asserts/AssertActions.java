package org.APIAutomationFramework.asserts;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertActions {

    public static void validateTheBodyData(String actual, String expected){
        assertThat(actual).isEqualTo(expected);
    }

    public static void validateTheBodyData(Integer actual, Integer expected){
        assertThat(actual).isEqualTo(expected);
    }
}
