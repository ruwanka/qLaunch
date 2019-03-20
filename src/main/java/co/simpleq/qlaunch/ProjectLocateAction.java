package co.simpleq.qlaunch;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.jetbrains.annotations.NotNull;

public class ProjectLocateAction extends AnAction {

    public ProjectLocateAction() {
        super("Locate Projects");
    }

    public void actionPerformed(@NotNull AnActionEvent event) {

//    ProjectManager projectManager = ProjectManager.getInstance();
//
//    try {
//      projectManager.loadAndOpenProject("C:\\Users\\Ruwanka\\Documents\\java-projects\\zerocode");
//    } catch (IOException e) {
//      e.printStackTrace();
//    } catch (JDOMException e) {
//      e.printStackTrace();
//    }

        Project project = event.getProject();

        ProgressManager.getInstance().run(new Task.Backgroundable(project,
            "Locating Intellij Idea Projects... ") {
            public void run(@NotNull ProgressIndicator indicator) {
                final QLaunchPluginState state = QLaunchSettings.getInstance().getState();

                final File[] files = File.listRoots();
                for(File file : files){
                    try {
                        Files.walkFileTree(file.toPath(), new ProjectLocator(state));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                indicator.setText("This is how you update the indicator");
                indicator.setIndeterminate(true);  // halfway done
            }
        });

        Messages
            .showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon());
    }
}