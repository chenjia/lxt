package com.lxt.ms.designpattern.factory.factorymethod;

import com.lxt.ms.designpattern.factory.ICourse;
import com.lxt.ms.designpattern.factory.JavaCourse;

/**
 * Created by Tom.
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
