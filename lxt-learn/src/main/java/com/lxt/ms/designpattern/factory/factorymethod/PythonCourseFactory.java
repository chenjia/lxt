package com.lxt.ms.designpattern.factory.factorymethod;

import com.lxt.ms.designpattern.factory.ICourse;
import com.lxt.ms.designpattern.factory.PythonCourse;

/**
 * Created by Tom.
 */
public class PythonCourseFactory implements ICourseFactory {

    public ICourse create() {
        return new PythonCourse();
    }
}
