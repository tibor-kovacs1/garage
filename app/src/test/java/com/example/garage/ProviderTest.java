package com.example.garage;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ProviderTest {

    private Provider provider;
    @Before
    public void setup() {
        provider = new Provider();
    }

    //todo check for some usable unit test path
    @Test
    public void providerExist(){
        assertThat(provider, instanceOf(Provider.class));
    }
}