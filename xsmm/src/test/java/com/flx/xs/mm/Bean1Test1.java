package com.flx.xs.mm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.flx.xs.xsmm.IBean1;

import static org.mockito.Mockito.*;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class Bean1Test1 {

    @Mock
    private IBean1 bean1;

    /**
     * http://docs.mockito.googlecode.com/hg/latest/org/mockito/Mockito.html
     */
    @Before
    public void before()
    {
         when(bean1.hello("world")).thenReturn("Hello, world");
    }

    @Test
    public void testHello()
    {
        System.out.println(getClass().getName() + ".testHello");
        //given
        String result;
        //when

        result = bean1.hello("world");

        //then
        assertThat(result).isEqualTo("Hello, worldxxxxccc");

    }
}
