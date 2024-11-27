package org.example.teamcitytesting.api;

import org.example.teamcitytesting.BaseTest;
import org.example.teamcitytesting.api.models.AuthModules;
import org.example.teamcitytesting.api.models.ServerAuthSettings;
import org.example.teamcitytesting.api.requests.ServerAuthRequest;
import org.example.teamcitytesting.api.spec.Specifications;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.example.teamcitytesting.generators.TestDataGenerator.generate;

public class BaseApiTest extends BaseTest {
    private final ServerAuthRequest serverAuthRequest = new ServerAuthRequest(Specifications.superUserSpec());
    private AuthModules authModules;
    private boolean perProjectPermissions;

    @BeforeSuite(alwaysRun = true)
    public void setUpServerAuthSettings() {
        // Получаем текущие настройки perProjectPermissions
        perProjectPermissions = serverAuthRequest.read().getPerProjectPermissions();

        authModules = generate(AuthModules.class);
        // Обновляем значение perProjectPermissions на true
        serverAuthRequest.update(ServerAuthSettings.builder()
                .perProjectPermissions(true)
                .modules(authModules)
                .build());
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUpServerAuthSettings() {
        // Возвращаем настройке perProjectPermissions исходное значение
        serverAuthRequest.update(ServerAuthSettings.builder()
                .perProjectPermissions(perProjectPermissions)
                .modules(authModules)
                .build());
    }
}
