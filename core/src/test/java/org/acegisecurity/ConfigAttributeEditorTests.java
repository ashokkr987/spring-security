/* Copyright 2004 Acegi Technology Pty Limited
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

package net.sf.acegisecurity;

import junit.framework.TestCase;

import java.util.Iterator;


/**
 * Tests {@link ConfigAttributeEditor} and associated {@link
 * ConfigAttributeDefinition}.
 *
 * @author Ben Alex
 * @version $Id$
 */
public class ConfigAttributeEditorTests extends TestCase {
    //~ Constructors ===========================================================

    public ConfigAttributeEditorTests() {
        super();
    }

    public ConfigAttributeEditorTests(String arg0) {
        super(arg0);
    }

    //~ Methods ================================================================

    public final void setUp() throws Exception {
        super.setUp();
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ConfigAttributeEditorTests.class);
    }

    public void testCorrectOperation() {
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        editor.setAsText("HELLO,DOCTOR,NAME,YESTERDAY,TOMORROW");

        ConfigAttributeDefinition result = (ConfigAttributeDefinition) editor
            .getValue();
        Iterator iter = result.getConfigAttributes();
        int position = 0;

        while (iter.hasNext()) {
            position++;
            iter.next();
        }

        assertEquals(5, position);
    }

    public void testEmptyStringReturnsNull() {
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        editor.setAsText("");

        ConfigAttributeDefinition result = (ConfigAttributeDefinition) editor
            .getValue();
        assertTrue(result == null);
    }

    public void testEqualsHandlingWhenDifferentObjectTypes() {
        ConfigAttributeDefinition def1 = new ConfigAttributeDefinition();
        def1.addConfigAttribute(new SecurityConfig("A"));
        def1.addConfigAttribute(new SecurityConfig("B"));

        assertTrue(!def1.equals("A_STRING"));
    }

    public void testEqualsHandlingWhenExactlyEqual() {
        ConfigAttributeDefinition def1 = new ConfigAttributeDefinition();
        def1.addConfigAttribute(new SecurityConfig("A"));
        def1.addConfigAttribute(new SecurityConfig("B"));

        ConfigAttributeDefinition def2 = new ConfigAttributeDefinition();
        def2.addConfigAttribute(new SecurityConfig("A"));
        def2.addConfigAttribute(new SecurityConfig("B"));

        assertEquals(def1, def2);
    }

    public void testEqualsHandlingWhenOrderingNotEqual() {
        ConfigAttributeDefinition def1 = new ConfigAttributeDefinition();
        def1.addConfigAttribute(new SecurityConfig("A"));
        def1.addConfigAttribute(new SecurityConfig("B"));

        ConfigAttributeDefinition def2 = new ConfigAttributeDefinition();
        def2.addConfigAttribute(new SecurityConfig("B"));
        def2.addConfigAttribute(new SecurityConfig("A"));

        assertTrue(!def1.equals(def2));
    }

    public void testEqualsHandlingWhenTestObjectHasNoAttributes() {
        ConfigAttributeDefinition def1 = new ConfigAttributeDefinition();
        def1.addConfigAttribute(new SecurityConfig("A"));
        def1.addConfigAttribute(new SecurityConfig("B"));

        ConfigAttributeDefinition def2 = new ConfigAttributeDefinition();

        assertTrue(!def1.equals(def2));
        assertTrue(!def2.equals(def1));
    }

    public void testNullReturnsNull() {
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        editor.setAsText(null);

        ConfigAttributeDefinition result = (ConfigAttributeDefinition) editor
            .getValue();
        assertTrue(result == null);
    }

    public void testToString() {
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        editor.setAsText("KOALA,KANGAROO,EMU,WOMBAT");

        ConfigAttributeDefinition result = (ConfigAttributeDefinition) editor
            .getValue();
        assertEquals("[KOALA, KANGAROO, EMU, WOMBAT]", result.toString());
    }
}
