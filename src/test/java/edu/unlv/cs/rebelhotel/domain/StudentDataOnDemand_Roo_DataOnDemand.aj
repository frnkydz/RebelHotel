// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.domain;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.TermDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect StudentDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StudentDataOnDemand: @Component;
    
    private Random StudentDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Student> StudentDataOnDemand.data;
    
    @Autowired
    private TermDataOnDemand StudentDataOnDemand.termDataOnDemand;
    
    public Student StudentDataOnDemand.getSpecificStudent(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Student obj = data.get(index);
        return Student.findStudent(obj.getId());
    }
    
    public Student StudentDataOnDemand.getRandomStudent() {
        init();
        Student obj = data.get(rnd.nextInt(data.size()));
        return Student.findStudent(obj.getId());
    }
    
    public boolean StudentDataOnDemand.modifyStudent(Student obj) {
        return false;
    }
    
    public void StudentDataOnDemand.init() {
        data = edu.unlv.cs.rebelhotel.domain.Student.findStudentEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Student' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<edu.unlv.cs.rebelhotel.domain.Student>();
        for (int i = 0; i < 10; i++) {
            edu.unlv.cs.rebelhotel.domain.Student obj = getNewTransientStudent(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
