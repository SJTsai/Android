package com.simon.practice.triviaforkids;

public class TFQuestion {

    private int mQuestion;
    private boolean mTrue;
    
    public TFQuestion(int questionResId, boolean isTrue) {
        mQuestion = questionResId;
        mTrue = isTrue;
    }
    
    public int getQuestion() {
        return mQuestion;
    }
    
    public void setQuestion(int question) {
        mQuestion = question;
    }
    
    public boolean isTrue() {
        return mTrue;
    }
    
    public void setTrue(boolean isTrue) {
        mTrue = isTrue;
    }
    
}
