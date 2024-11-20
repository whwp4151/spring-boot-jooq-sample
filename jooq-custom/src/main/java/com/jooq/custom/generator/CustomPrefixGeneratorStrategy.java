package com.jooq.custom.generator;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

/**
 * Description : Jooq code generator 시에 기본 Model 이랑 구분하기 위한 커스텀 클레스
 */
public class CustomPrefixGeneratorStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {

        if(mode == Mode.DEFAULT){
            return "J" + super.getJavaClassName(definition, mode);
        }else if (mode == Mode.POJO){
            return super.getJavaClassName(definition, mode) + "Pojo";
        }

        return super.getJavaClassName(definition, mode);
    }

}
