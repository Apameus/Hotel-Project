package com.apameus.gb_hotel_java_fx.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OrderSerializerTest {

    @Mock
    List<String> mockList;

    @Test
    void mock_list_returns_correct_element(){
        MockitoAnnotations.initMocks(this);
        when(mockList.get(0)).thenReturn("Like");
        assertEquals("Like", mockList.get(0));
    }



}