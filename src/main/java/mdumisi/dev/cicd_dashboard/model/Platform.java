package mdumisi.dev.cicd_dashboard.model;

public enum Platform {
    GITLAB_CI("Gitlan CI"),
    GITHUB_ACTIONS("Github Actions"),
    AZURE_DEVOPS("Azure_Devops"),
    JENKINS("Jenkins");

    private final String displayName;

    Platform(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
