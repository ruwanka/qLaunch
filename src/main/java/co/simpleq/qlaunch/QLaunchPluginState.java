package co.simpleq.qlaunch;

import java.util.HashMap;
import java.util.Map;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchPluginState {

    private Map<String, String> ideaProjects = new HashMap<>();

    @SuppressWarnings("WeakerAccess")
    public Map<String, String> getIdeaProjects() {
        return ideaProjects;
    }

    public void setIdeaProjects(Map<String, String> ideaProjects) {
        this.ideaProjects = ideaProjects;
    }

    @SuppressWarnings("WeakerAccess")
    public void addProject(String project, String path) {
        this.ideaProjects.put(project, path);
    }
}