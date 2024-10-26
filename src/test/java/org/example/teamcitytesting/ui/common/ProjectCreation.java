package org.example.teamcitytesting.ui.common;

import com.codeborne.selenide.Condition;
import org.example.teamcitytesting.api.ProjectPage;
import org.example.teamcitytesting.api.models.Project;
import org.example.teamcitytesting.enums.Endpoint;
import org.example.teamcitytesting.ui.BaseUiTest;
import org.example.teamcitytesting.ui.pages.ProjectsPage;
import org.example.teamcitytesting.ui.pages.admin.CreateProjectPage;

import static io.qameta.allure.Allure.step;

public class ProjectCreation extends BaseUiTest {

    public void createProjectWithChecks(String repo_url, String projectName, String buildTypeName) {
        //ui interations
        CreateProjectPage.open("_Root")
                .createForm(repo_url)
                .setupProject(projectName, buildTypeName);
        // api checks
        step("Check that all entities (project, build type) were successfully created with correct data on API lvl");
        var createdProject = superUserCheckRequest.<Project>getRequest(Endpoint.PROJECT).read("name:" + projectName);
        //ui check
        step("Check that project is visible on Projects page ");
        ProjectPage.open(createdProject.getId())
                .title.shouldHave(Condition.exactText(projectName));

        //var projectExists = ProjectsPage.open().getProjects().stream().anyMatch(project -> project.getName().getText().equals(projectName));
    }


    public void createProjectNoChecks(String repo_url, String projectName, String buildTypeName) {
        CreateProjectPage.open("_Root")
                .createForm(repo_url)
                .setupProject(projectName, buildTypeName);
    }

    public boolean checkProjectExists(String projectName) {
        return ProjectsPage.open().getProjects().stream().anyMatch(project -> project.getName().getText().equals(projectName));
    }
}
