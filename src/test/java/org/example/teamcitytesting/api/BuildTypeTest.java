package org.example.teamcitytesting.api;

import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

@Test(groups = "Regression")
public class BuildTypeTest extends BaseApiTest {
    @Test(description = "User should be able to create a build", groups = {"Positive", "CRUD"})
    public void userCreatesBuildTypeTest() {
        step("Create user");
        step("Create project by user");
        step("Create buildType project by user");
        step("Check that build type was created succesfully with correct data");
    }

    @Test(description = "User should not be able to create two build typees with the same id", groups = {"Negative", "CRUD"})
    public void userCreatesTwoBuildTypeWithTheSameIdTest() {
        step("Create user");
        step("Create project by user");
        step("Create buildType1 project by user");
        step("Create buildType2 with same id as buildType2 project by user");
        step("Check that build type was not created with 400 code");
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
