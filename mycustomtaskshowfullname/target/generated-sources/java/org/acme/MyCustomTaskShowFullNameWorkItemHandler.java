/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme;

import java.util.HashMap;
import java.util.Map;
import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.jbpm.process.workitem.core.util.RequiredParameterValidator;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.jbpm.process.workitem.core.util.Wid;
import org.jbpm.process.workitem.core.util.WidParameter;
import org.jbpm.process.workitem.core.util.WidResult;
import org.jbpm.process.workitem.core.util.service.WidAction;
import org.jbpm.process.workitem.core.util.service.WidAuth;
import org.jbpm.process.workitem.core.util.service.WidService;
import org.jbpm.process.workitem.core.util.WidMavenDepends;

@Wid(widfile="MyCustomTaskShowFullNameDefinitions.wid", name="MyCustomTaskShowFullNameDefinitions",
        displayName="MyCustomTaskShowFullNameDefinitions",
        defaultHandler="mvel: new org.acme.MyCustomTaskShowFullNameWorkItemHandler()",
        documentation = "mycustomtaskshowfullname/index.html",
        category = "mycustomtaskshowfullname",
        icon = "MyCustomTaskShowFullNameDefinitions.png",
        parameters={
            @WidParameter(name="SampleParam", required = true),
            @WidParameter(name="SampleParamTwo", required = true)
        },
        results={
            @WidResult(name="SampleResult")
        },
        mavenDepends={
            @WidMavenDepends(group="org.acme", artifact="mycustomtaskshowfullname", version="5.0.0-SNAPSHOT")
        },
        serviceInfo = @WidService(category = "mycustomtaskshowfullname", description = "Workitem Desciption",
                keywords = "",
                action = @WidAction(title = "Sample Title"),
                authinfo = @WidAuth(required = true, params = {"SampleParam", "SampleParamTwo"},
                        paramsdescription = {"SampleParam", "SampleParamTwo"},
                        referencesite = "referenceSiteURL")
        )
)
public class MyCustomTaskShowFullNameWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {
        private String sampleParam;
        private String sampleParamTwo;

    public MyCustomTaskShowFullNameWorkItemHandler(String SampleParam, String SampleParamTwo){
            this.sampleParam = sampleParam;
            this.sampleParamTwo = sampleParamTwo;
        }
    public MyCustomTaskShowFullNameWorkItemHandler(){
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            RequiredParameterValidator.validate(this.getClass(), workItem);

            // sample parameters
            sampleParam = (String) workItem.getParameter("SampleParam");
            sampleParamTwo = (String) workItem.getParameter("SampleParamTwo");

            // complete workitem impl...

            // return results
            String sampleResult = sampleParam +" " + sampleParamTwo;
            Map<String, Object> results = new HashMap<String, Object>();
            results.put("SampleResult", sampleResult);
            manager.completeWorkItem(workItem.getId(), results);
        } catch(Throwable cause) {
            handleException(cause);
        }
    }

    @Override
    public void abortWorkItem(WorkItem workItem,
                              WorkItemManager manager) {
        // stub
    }
}


