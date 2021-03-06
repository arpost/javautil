package org.arp.javautil.sql;

/*-
 * #%L
 * JavaUtil
 * %%
 * Copyright (C) 2012 - 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.mockrunner.mock.jdbc.JDBCMockObjectFactory;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests whether {@link ConnectionSpec} returns correct database metadata for
 * the MockRunner database driver.
 * 
 * @author Andrew Post
 */
public class MockRunnerDatabaseMetaDataTest 
        extends AbstractDatabaseMetaDataTest {

    private static JDBCMockObjectFactory JDBC_MOCK_OBJECT_FACTORY;

    @BeforeClass
    public static void setUpClass() throws InvalidConnectionSpecArguments {
        JDBC_MOCK_OBJECT_FACTORY = new JDBCMockObjectFactory();
        JDBC_MOCK_OBJECT_FACTORY.registerMockDriver();
        createConnectionSpecInstance("", null, null);
    }

    @AfterClass
    public static void tearDownClass() {
        destroyConnectionSpecInstance();
        JDBC_MOCK_OBJECT_FACTORY.restoreDrivers();
        JDBC_MOCK_OBJECT_FACTORY = null;
    }

    @Test
    public void testDatabaseProduct() throws SQLException {
        assertDatabaseProduct(DatabaseProduct.OTHER);
    }

    @Test
    public void testDatabaseVersion() throws SQLException {
        assertDatabaseVersion(new DatabaseVersion(1, 0, "1.0"));
    }

    @Test
    public void testDriver() throws SQLException {
        assertDriver(Driver.OTHER);
    }

    @Test
    public void testDriverVersion() throws SQLException {
        assertDriverVersion(new DriverVersion(1, 0, "1.0"));
    }
}
