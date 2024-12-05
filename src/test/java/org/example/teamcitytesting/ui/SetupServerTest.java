package org.example.teamcitytesting.ui;

import org.example.teamcitytesting.ui.setup.FirstStartPage;
import org.testng.annotations.Test;

public class SetupServerTest extends BaseUiTest {
    @Test(groups = {"Setup"})
    public void setupTeamCityServerTest(){
        FirstStartPage.open().setupFirstStart();
    }
}
