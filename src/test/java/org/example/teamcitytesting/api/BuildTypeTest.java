package org.example.teamcitytesting.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.example.teamcitytesting.api.models.*;
import org.example.teamcitytesting.api.requests.CheckedRequests;
import org.example.teamcitytesting.api.requests.UncheckedRequests;
import org.example.teamcitytesting.api.requests.unchecked.UncheckedBase;
import org.example.teamcitytesting.api.spec.Specifications;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static io.qameta.allure.Allure.step;
import static org.example.teamcitytesting.enums.Endpoint.*;
import static org.example.teamcitytesting.generators.TestDataGenerator.generate;

@Test(groups = "Regression")
public class BuildTypeTest extends BaseApiTest {
    @Test(description = "User should be able to create a build", groups = {"Positive", "CRUD"})
    public void userCreatesBuildTypeTest() {

        superUserCheckRequest.getRequest(USERS).create(testData.getUser());
        var userCheckRequest = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        userCheckRequest.<Project>getRequest(PROJECT).create(testData.getProject());

        userCheckRequest.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var createdBuildType = userCheckRequest.<BuildType>getRequest(BUILD_TYPES).read("id:" + testData.getBuildType().getId());

        softy.assertEquals(testData.getBuildType().getName(), createdBuildType.getName(), "Build type Name is not correct");
    }

    @Test(description = "User should not be able to create two build typees with the same id", groups = {"Negative", "CRUD"})
    public void userCreatesTwoBuildTypeWithTheSameIdTest() {

        var buildTypeWithSameId = generate(Arrays.asList(testData.getProject()), BuildType.class, testData.getBuildType().getId());

        superUserCheckRequest.getRequest(USERS).create(testData.getUser());

        var userCheckRequests = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        userCheckRequests.<Project>getRequest(PROJECT).create(testData.getProject());

        userCheckRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());
        new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES)
                .create(buildTypeWithSameId)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("The build configuration / template ID \"%s\" is already used by another configuration or template".formatted(testData.getBuildType().getId())));
    }

    @Test(description = "Project admin should be able to create build type for their project", groups = {"Positive", "Roles"})
    public void projectAdminCreatesBuildTypeTest() {

        superUserCheckRequest.getRequest(PROJECT).create(testData.getProject());

        var userCheckRequest = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        testData.getUser().setRoles(generate(Roles.class, "PROJECT_ADMIN", "p:" + testData.getProject().getId()));

        superUserCheckRequest.<User>getRequest(USERS).create(testData.getUser());

        userCheckRequest.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var createdBuildType = userCheckRequest.<BuildType>getRequest(BUILD_TYPES).read("id:" + testData.getBuildType().getId());

        softy.assertEquals(testData.getBuildType().getName(), createdBuildType.getName(), "Build type Name is not correct");
    }

    @Test(description = "Project admin should not be able to create build type for not their project", groups = {"Negative", "Roles"})
    public void projectAdminCreatesBuildTypeForAnotherProjectTest() {

        var testData2 =  generate();

        superUserCheckRequest.getRequest(USERS).create(testData.getUser());
        superUserCheckRequest.getRequest(USERS).create(testData2.getUser());

        var userCheckRequest = new CheckedRequests(Specifications.authSpec(testData.getUser()));
        var userCheckRequest2 = new CheckedRequests(Specifications.authSpec(testData2.getUser()));

        testData.getUser().setRoles(generate(Roles.class, "PROJECT_ADMIN", "p:" + testData.getProject().getId()));
        testData2.getUser().setRoles(generate(Roles.class, "PROJECT_ADMIN", "p:" + testData2.getProject().getId()));


        userCheckRequest.<Project>getRequest(PROJECT).create(testData.getProject());

        new UncheckedBase(Specifications.authSpec(testData.getUser()), BUILD_TYPES)
                .create(testData2.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString(
                        "Could not find the entity requested. Check the reference is correct and the user has permissions to access the entity."));//  var createdBuildType = userCheckRequest.<BuildType>getRequest(BUILD_TYPES).read(testData2.getBuildType().getId());

    }
}
