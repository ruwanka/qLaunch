package co.simpleq.qlaunch;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchProjectOpenAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (e.getProject() == null) {
            showError(editor, "failed to identify project!");
            return;
        }

        final QLaunchSettings qLaunchSettings = QLaunchSettings.getInstance();
        if (qLaunchSettings != null) {
            final QLaunchPluginState state = qLaunchSettings.getState();
            showProjectList(e.getProject(), editor, state);
        } else {
            showError(editor, "failed to load qLaunch settings");
        }
    }

    private void showProjectList(Project project, Editor editor,
        QLaunchPluginState state) {
        if (state != null && !state.getIdeaProjects().isEmpty()) {
            final String[] projects = state.getIdeaProjects().keySet().toArray(new String[0]);
            final QLaunchProjectListPopupStep step = new QLaunchProjectListPopupStep("Open Project",
                projects);
            JBPopupFactory.getInstance().createListPopup(step, 10)
                .showCenteredInCurrentWindow(project);
        } else {
            showError(editor, "Locate Projects (ctrl + alt + Q) or Tools -> Locate Projects!");
        }
    }

    private void showError(Editor editor, String message) {
        HintManager.getInstance().showErrorHint(editor, message, HintManager.RIGHT_UNDER);
    }
}
