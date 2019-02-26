package com.hand.hjmall.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
* @Description:    状态标记校验器
* @CreateDate:     2019/2/21 19:31
* @CreateUser:     SilenceTian
* @Version:        1.0
*/
public class FlagValidatorClass implements ConstraintValidator<FlagValidator,Integer> {
    private String[] values;
    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if(value==null){
            //当状态为空时使用默认值
            return true;
        }
        for(int i=0;i<values.length;i++){
            if(values[i].equals(String.valueOf(value))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
