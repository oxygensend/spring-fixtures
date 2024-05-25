package com.oxygensend.springfixtures;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixturesLoaderTest {

    @Mock
    private Fixture businessObjectFixture;
    @Mock
    private Fixture dictionaryFixture;

    @InjectMocks
    private FixturesLoader fixtureLoader;

    @BeforeEach
    void setUp() {
        when(dictionaryFixture.type()).thenReturn(FixtureType.DICTIONARY);
        when(businessObjectFixture.type()).thenReturn(FixtureType.BUSINESS_OBJECT);

        fixtureLoader = new FixturesLoader(List.of(businessObjectFixture, dictionaryFixture));
    }

    @Test
    void testLoad() {

        fixtureLoader.load();
        InOrder inOrder = inOrder(dictionaryFixture, businessObjectFixture);
        inOrder.verify(dictionaryFixture).load();
        inOrder.verify(businessObjectFixture).load();

        verify(dictionaryFixture, times(1)).load();
        verify(businessObjectFixture, times(1)).load();

    }

}
