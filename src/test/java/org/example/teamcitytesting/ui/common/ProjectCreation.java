package org.example.teamcitytesting.ui.common;

import com.codeborne.selenide.Condition;
import org.example.teamcitytesting.BaseTest;
import org.example.teamcitytesting.api.ProjectPage;
import org.example.teamcitytesting.api.models.Project;
import org.example.teamcitytesting.enums.Endpoint;
import org.example.teamcitytesting.ui.BaseUiTest;
import org.example.teamcitytesting.ui.pages.admin.CreateProjectPage;

import static io.qameta.allure.Allure.step;

public class ProjectCreation extends BaseUiTest {
    
    public void createProject(String repo_url) {
        //ui interations
        CreateProjectPage.open("_Root")
                .createForm(repo_url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());
        // api checks
        step("Check that all entities (project, build type) were successfully created with correct data on API lvl");
        var createdProject = superUserCheckRequest.<Project>getRequest(Endpoint.PROJECT).read("name:" + testData.getProject().getName());
        softy.assertNotNull(createdProject);
        //ui check
        step("Check that project is visible on Projects page ");
        ProjectPage.open(createdProject.getId())
                .title.shouldHave(Condition.exactText(testData.getProject().getName()));
    }
 }
