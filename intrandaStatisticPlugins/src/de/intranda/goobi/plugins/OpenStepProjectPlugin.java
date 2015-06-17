package de.intranda.goobi.plugins;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.xeoh.plugins.base.annotations.PluginImplementation;

import org.apache.log4j.Logger;
import org.goobi.beans.Project;
import org.goobi.production.plugin.interfaces.AbstractStatisticsPlugin;
import org.goobi.production.plugin.interfaces.IStatisticPlugin;

import de.intranda.goobi.plugins.util.ProjectData;
import de.sub.goobi.persistence.managers.ProjectManager;

@PluginImplementation
public class OpenStepProjectPlugin extends AbstractStatisticsPlugin implements IStatisticPlugin {

    private static final String PLUGIN_TITLE = "OpenStepProjectPlugin";

    private static final Logger logger = Logger.getLogger(OpenStepProjectPlugin.class);

    private List<ProjectData> projectDataList = new ArrayList<ProjectData>();

    public void initProjectData() {
        List<Project> projectList = ProjectManager.getAllProjects();

        for (Project project : projectList) {
            ProjectData pd = new ProjectData();
            pd.setProject(project);
            projectDataList.add(pd);
        }
    }

    @Override
    public void calculate() {

        for (ProjectData pd : projectDataList) {
            if (pd.isSelected()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Calculating data for project " + pd.getProject().getTitel());
                }
                pd.calculateOpenSteps();
            }
        }
    }

    @Override
    public String getTitle() {
        return PLUGIN_TITLE;
    }

    @Override
    public String getGui() {
        return "/uii/opensteps_statistics.xhtml";
    }

    @Override
    public void setStartDate(Date date) {

    }

    @Override
    public void setEndDate(Date date) {

    }

    public List<ProjectData> getProjectDataList() {
        if (projectDataList.isEmpty()) {
            initProjectData();
        }
        return projectDataList;
    }

    public void setProjectDataList(List<ProjectData> projectDataList) {
        this.projectDataList = projectDataList;
    }

    @Override
    public String getData() {
        // TODO Auto-generated method stub
        return null;
    }

}