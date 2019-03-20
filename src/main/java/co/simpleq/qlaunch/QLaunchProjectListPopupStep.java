package co.simpleq.qlaunch;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import java.io.IOException;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchProjectListPopupStep extends BaseListPopupStep<String> {

    public QLaunchProjectListPopupStep(@Nullable String title,
        @NotNull String... values) {
        super(title, values);
    }

    @Override
    public PopupStep onChosen(String selectedValue, boolean finalChoice) {
        ProjectManager projectManager = ProjectManager.getInstance();
        final QLaunchSettings qLaunchSettings = QLaunchSettings.getInstance();

        if (qLaunchSettings != null){
            final QLaunchPluginState state = qLaunchSettings.getState();
            if (state != null && !state.getIdeaProjects().isEmpty()){
                final String projectDir = state.getIdeaProjects().get(selectedValue);
                doFinalStep(() -> {
                    try {
                        final Project project = projectManager
                            .loadAndOpenProject(projectDir);
                    } catch (
                        IOException e) {
                        e.printStackTrace();
                    } catch (
                        JDOMException e) {
                        e.printStackTrace();
                    }
                });
            }
        }


        return PopupStep.FINAL_CHOICE;
    }
}
