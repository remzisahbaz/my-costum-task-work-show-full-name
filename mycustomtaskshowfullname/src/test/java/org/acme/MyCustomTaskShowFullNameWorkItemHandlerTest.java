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

import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.core.TestWorkItemManager;
import org.jbpm.test.AbstractBaseTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyCustomTaskShowFullNameWorkItemHandlerTest extends AbstractBaseTest {

    @Test
    public void testHandler() {
        WorkItemImpl workItem = new WorkItemImpl();
        workItem.setParameter("FirstName", "Remzi");
        workItem.setParameter("LastName", "ŞAHBAZ");
        TestWorkItemManager manager = new TestWorkItemManager();
        MyCustomTaskShowFullNameWorkItemHandler handler = new MyCustomTaskShowFullNameWorkItemHandler();
        handler.setLogThrownException(true);
        handler.executeWorkItem(workItem, manager);
        assertNotNull(manager.getResults());
        assertEquals(1, manager.getResults().size());
        assertEquals("Remzi ŞAHBAZ", manager.getResults().get(0L).get("FullName"));
        assertTrue(manager.getResults().containsKey(workItem.getId()));
    }
}
