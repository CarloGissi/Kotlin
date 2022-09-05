package com.example.mydevice

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import com.example.mydevice.ui.pc.PcFragment
import com.example.mydevice.ui.pc.PcNewActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest{

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)


    //serve per fare vedere che il MainActivity si avvia
    @Test
    fun checkMainActivity(){
        onView(withId(R.id.container))
            .check(matches(isDisplayed()))
    }

    //test che fa vedere che si avvia sulla prima schermata
    @Test
    fun checkFragment(){
        onView(withId(R.id.container))
            .check(matches(isDisplayed()))
        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.nav_host_fragment_activity_main))
            .check(matches(isDisplayed()))
        onView(withId(R.id.navigation_pc))
            .check(matches(isDisplayed()))
    }

}