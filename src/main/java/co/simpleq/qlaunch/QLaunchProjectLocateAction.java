package co.simpleq.qlaunch;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jetbrains.annotations.NotNull;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchProjectLocateAction extends AnAction {

    public QLaunchProjectLocateAction() {
        super("Locate Projects");
    }

    public void actionPerformed(@NotNull AnActionEvent event) {

        final FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(
            false,
            true,
            false,
            false,
            false,
            false
        );

        fileChooserDescriptor.setTitle("Locate Projects");
        fileChooserDescriptor.setDescription("Select directory to locate projects, "
            + "if entire directory needs to be scanned select drive");

        Project project = event.getProject();

        if (project == null) {
            return;
        }

        FileChooser.chooseFile(fileChooserDescriptor, null, null, virtualFile -> {
            scanDirectory(project, virtualFile.getPath());
        });

    }

    private void scanDirectory(@NotNull Project project, String path) {
        ProgressManager.getInstance().run(new Task.Backgroundable(project,
            "Locating Intellij Idea Projects... ") {
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                final QLaunchPluginState state = QLaunchSettings.getInstance().getState();
                try {
                    Files.walkFileTree(Paths.get(path), new QLaunchProjectLocator(state));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                indicator.setText("Finished locating Intellij Idea Projects...");
            }
        });
    }
}