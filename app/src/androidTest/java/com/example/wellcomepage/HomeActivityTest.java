package com.example.wellcomepage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testSuccessfulLogin() {
        // Simulate user input
        onView(withId(R.id.username)).perform(replaceText("Devin Angel"));
        onView(withId(R.id.password)).perform(replaceText("s4678590"));

        // Simulate button click
        onView(withId(R.id.loginButton)).perform(click());

    }

    @Test
    public void testFailedLogin() {
        // Simulate user input for invalid credentials
        onView(withId(R.id.username)).perform(replaceText("devdev"));
        onView(withId(R.id.password)).perform(replaceText("******"));

        // Simulate button click
        onView(withId(R.id.loginButton)).perform(click());


    }
}