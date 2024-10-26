package org.example.teamcitytesting.ui;

import com.codeborne.selenide.Condition;
import org.example.teamcitytesting.api.ProjectPage;
import org.example.teamcitytesting.api.models.Project;
import org.example.teamcitytesting.enums.Endpoint;
import org.example.teamcitytesting.ui.pages.LoginPage;
import org.example.teamcitytesting.ui.pages.ProjectsPage;
import org.example.teamcitytesting.ui.pages.admin.CreateProjectPage;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

@Test(groups = {"Regression"})
public class CreateProjectTest extends BaseUiTest {

    private static final String REPO_URL = "https://github.com/kimbyb/gallery.git";

    @Test(description = "User should be able to create project", groups = {"Positive"})
    public void userCreatesProject() {
        // preparation

        loginAs(testData.getUser());

        //ui interations
        CreateProjectPage.open("_Root")
                .createForm(REPO_URL)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());
        // api checks
        step("Check that all entities (project, build type) were successfully created with correct data on API lvl");
        var createdProject = superUserCheckRequest.<Project>getRequest(Endpoint.PROJECT).read("name:" + testData.getProject().getName());
        softy.assertNotNull(createdProject);
        //ui check
        step("Check that project is visible on Projects page ");
        ProjectPage.open(createdProject.getId())
                .title.shouldHave(Condition.exactText(testData.getProject().getName()));

        var projectExists = ProjectsPage.open().getProjects().stream().anyMatch(project -> project.getName().getText().equals(testData.getProject().getName()));
        softy.assertTrue(projectExists);
    }

    @Test(description = "User should not be able to create project without name", groups = {"Negative"})
    public void userCreatesProjectWithoutName() {
        // preparation
        step("Login as user");

        step("Check number of projects");

        //ui interations
        step("Open Create project page ");
        step("Send all project parameters (repo url)");
        step("Click proceed");
        step("Set project name to empty");
        step("Click proceed");

        // api checks
        step("Check that number of projects did not change");

        //UI check
        step("Check that error appears Project name must not be empty");
    }
}
