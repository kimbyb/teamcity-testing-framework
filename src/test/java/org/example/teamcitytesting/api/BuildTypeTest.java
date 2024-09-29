package org.example.teamcitytesting.api;

import org.apache.http.HttpStatus;
import org.example.teamcitytesting.api.models.BuildType;
import org.example.teamcitytesting.api.models.Project;
import org.example.teamcitytesting.api.models.User;
import org.example.teamcitytesting.api.requests.CheckedRequests;
import org.example.teamcitytesting.api.requests.unchecked.UncheckedBase;
import org.example.teamcitytesting.api.spec.Specifications;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;

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

        var createdBuildType = userCheckRequest.<BuildType>getRequest(BUILD_TYPES).read(testData.getBuildType().getId());

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
        step("Create user");
        step("Create project");
        step("Grant user PROJECT_ADMIN in project");
        step("Create buildType project by user (PROJECT_ADMIN)");
        step("Check that build type was created succesfully");
    }

    @Test(description = "Project admin should not be able to create build type for not their project", groups = {"NEgative", "Roles"})
    public void projectAdminCreatesBuildTypeForAnotherProjectTest() {
        step("Create user1");
        step("Create project1");
        step("Grant user1 PROJECT_ADMIN in project1");

        step("Create user2");
        step("Create project2");
        step("Grant user2 PROJECT_ADMIN in project2");

        step("Create buildType project1 by user2");
        step("Check that build type was not created with forbitten code");
    }
}
