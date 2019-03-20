package co.simpleq.qlaunch;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class ProjectOpenAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        if (e.getProject() == null) {
            return;
        }
        final DataContext dataContext = e.getDataContext();

        final QLaunchSettings qLaunchSettings = QLaunchSettings.getInstance();
        if (qLaunchSettings != null){
            final QLaunchPluginState state = qLaunchSettings.getState();
            if (state != null && !state.getIdeaProjects().isEmpty()){
                final QLaunchProjectListPopupStep step = new QLaunchProjectListPopupStep("Open Project",state.getIdeaProjects().keySet().toArray(new String[0]));
                JBPopupFactory.getInstance().createListPopup(step, 10).showCenteredInCurrentWindow(e.getProject());
            }
        }

//        JBPopupFactory.getInstance().createActionGroupPopup("Open Project", ActionGroup.EMPTY_GROUP, , ActionSelectionAid.SPEEDSEARCH, false).showCenteredInCurrentWindow(e.getProject());


//JBPopupFactory.getInstance().createActionGroupPopup("test", ActionGroup.)





    }

    private void showError(){

    }
}
