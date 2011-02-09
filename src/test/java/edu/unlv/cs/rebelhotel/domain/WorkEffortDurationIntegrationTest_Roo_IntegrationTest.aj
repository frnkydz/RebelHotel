// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.domain;

import edu.unlv.cs.rebelhotel.domain.WorkEffortDurationDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect WorkEffortDurationIntegrationTest_Roo_IntegrationTest {
    
    declare @type: WorkEffortDurationIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: WorkEffortDurationIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: WorkEffortDurationIntegrationTest: @Transactional;
    
    @Autowired
    private WorkEffortDurationDataOnDemand WorkEffortDurationIntegrationTest.dod;
    
    @Test
    public void WorkEffortDurationIntegrationTest.testCountWorkEffortDurations() {
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", dod.getRandomWorkEffortDuration());
        long count = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.countWorkEffortDurations();
        org.junit.Assert.assertTrue("Counter for 'WorkEffortDuration' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testFindWorkEffortDuration() {
        edu.unlv.cs.rebelhotel.domain.WorkEffortDuration obj = dod.getRandomWorkEffortDuration();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to provide an identifier", id);
        obj = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findWorkEffortDuration(id);
        org.junit.Assert.assertNotNull("Find method for 'WorkEffortDuration' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'WorkEffortDuration' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testFindAllWorkEffortDurations() {
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", dod.getRandomWorkEffortDuration());
        long count = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.countWorkEffortDurations();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'WorkEffortDuration', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<edu.unlv.cs.rebelhotel.domain.WorkEffortDuration> result = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findAllWorkEffortDurations();
        org.junit.Assert.assertNotNull("Find all method for 'WorkEffortDuration' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'WorkEffortDuration' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testFindWorkEffortDurationEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", dod.getRandomWorkEffortDuration());
        long count = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.countWorkEffortDurations();
        if (count > 20) count = 20;
        java.util.List<edu.unlv.cs.rebelhotel.domain.WorkEffortDuration> result = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findWorkEffortDurationEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'WorkEffortDuration' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'WorkEffortDuration' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testFlush() {
        edu.unlv.cs.rebelhotel.domain.WorkEffortDuration obj = dod.getRandomWorkEffortDuration();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to provide an identifier", id);
        obj = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findWorkEffortDuration(id);
        org.junit.Assert.assertNotNull("Find method for 'WorkEffortDuration' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyWorkEffortDuration(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'WorkEffortDuration' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testMerge() {
        edu.unlv.cs.rebelhotel.domain.WorkEffortDuration obj = dod.getRandomWorkEffortDuration();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to provide an identifier", id);
        obj = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findWorkEffortDuration(id);
        boolean modified =  dod.modifyWorkEffortDuration(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        edu.unlv.cs.rebelhotel.domain.WorkEffortDuration merged = (edu.unlv.cs.rebelhotel.domain.WorkEffortDuration) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'WorkEffortDuration' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", dod.getRandomWorkEffortDuration());
        edu.unlv.cs.rebelhotel.domain.WorkEffortDuration obj = dod.getNewTransientWorkEffortDuration(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'WorkEffortDuration' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'WorkEffortDuration' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void WorkEffortDurationIntegrationTest.testRemove() {
        edu.unlv.cs.rebelhotel.domain.WorkEffortDuration obj = dod.getRandomWorkEffortDuration();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'WorkEffortDuration' failed to provide an identifier", id);
        obj = edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findWorkEffortDuration(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'WorkEffortDuration' with identifier '" + id + "'", edu.unlv.cs.rebelhotel.domain.WorkEffortDuration.findWorkEffortDuration(id));
    }
    
}