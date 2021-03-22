package com.mdit.library;

public interface EnhancerInterface {

    public void setMethodInterceptor$Enhancer$(MethodInterceptor methodInterceptor);

    /**
     *
     */
    public void setCallBacksMethod$Enhancer$(MethodInterceptor[] methodInterceptor);

    public Object getTarget$Enhancer$();

    public void setTarget$Enhancer$(Object o);

    /**
     * filter
     */
    public void setCallBackFilterMethod$Enhancer$(CallbackFilter callbackFilter);

}
