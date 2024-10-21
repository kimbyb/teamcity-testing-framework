package org.example.teamcitytesting.api;

import org.example.teamcitytesting.api.models.TestData;
import org.example.teamcitytesting.api.requests.CheckedRequests;
import org.example.teamcitytesting.api.spec.Specifications;
import org.example.teamcitytesting.generators.TestDataStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.example.teamcitytesting.generators.TestDataGenerator.generate;

public class BaseTest {
    protected SoftAssert softy;
    protected CheckedRequests superUserCheckRequest = new CheckedRequests(Specifications.superUserSpec());
    protected TestData testData;


    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {

        softy = new SoftAssert();
        testData =  generate();

    }



    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        softy.assertAll();
        TestDataStorage.getStorage().deleteCreatedEntities();
    }
}
