package co.simpleq.qlaunch;

import java.util.HashMap;
import java.util.Map;

public class QLaunchPluginState {

    private String pluginVersion = "";
    private boolean enableRuntimePatch = false;
    private Map<String, String> ideaProjects = new HashMap<>();

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public boolean isEnableRuntimePatch() {
        return enableRuntimePatch;
    }

    public void setEnableRuntimePatch(boolean enableRuntimePatch) {
        this.enableRuntimePatch = enableRuntimePatch;
    }

    public Map<String, String> getIdeaProjects() {
        return ideaProjects;
    }

    public void setIdeaProjects(Map<String, String> ideaProjects) {
        this.ideaProjects = ideaProjects;
    }

    public void addProject(String project, String path) {
        this.ideaProjects.put(project, path);
    }
}