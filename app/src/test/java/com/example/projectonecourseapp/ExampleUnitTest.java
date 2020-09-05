package com.example.projectonecourseapp;

import android.content.Context;

import com.example.projectonecourseapp.db.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void createUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testUsername() {
        User user = new User();
        user.setUserName("din_djarin");
        assertEquals("din_djarin", user.getUserName());
    }

    @Test
    public void testPassword() {
        User user = new User();
        user.setPassword("baby_yoda_ftw");
        assertEquals("baby_yoda_ftw", user.getPassword());
    }



}