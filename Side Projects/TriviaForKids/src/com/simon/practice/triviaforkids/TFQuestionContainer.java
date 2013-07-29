package com.simon.practice.triviaforkids;

import android.content.Context;
import android.util.SparseArray;

public class TFQuestionContainer {
    
    private static TFQuestionContainer sTFQuestionContainer;
    private SparseArray<TFQuestion[]> mQuestions;
    private final int mQuestionsPerSet = 10;
    private Context mAppContext;
    
    private TFQuestionContainer(Context appContext) {
        mAppContext = appContext;
        mQuestions = new SparseArray<TFQuestion[]>();
        int numQuestionSet = 0;
        
        TFQuestion[] questionSet = new TFQuestion[] { new TFQuestion(R.string.sky_color_q, false),
                                                      new TFQuestion(R.string.bird_leg_q, true),
                                                      new TFQuestion(R.string.monday_tuesday_q, false),
                                                      new TFQuestion(R.string.elephant_skin_q, true),
                                                      new TFQuestion(R.string.elmo_color_q, true),
                                                      new TFQuestion(R.string.cookie_monster_color, false),
                                                      new TFQuestion(R.string.oscar_color, true),
                                                      new TFQuestion(R.string.pigs_oink, true),
                                                      new TFQuestion(R.string.cats_woof, false),
                                                      new TFQuestion(R.string.christmas_december, true) };
        mQuestions.append(numQuestionSet++, questionSet);
        
        questionSet = new TFQuestion[] { new TFQuestion(R.string.x_w, false),
                                         new TFQuestion(R.string.seven_eight, false),
                                         new TFQuestion(R.string.bird_chirp, true),
                                         new TFQuestion(R.string.sheep_baah, true),
                                         new TFQuestion(R.string.car_wheels, false),
                                         new TFQuestion(R.string.bike_wheels, true),
                                         new TFQuestion(R.string.one_plus_one, true),
                                         new TFQuestion(R.string.circle_round, true),
                                         new TFQuestion(R.string.firetruck_yellow, false),
                                         new TFQuestion(R.string.five_toes, true) };
        mQuestions.append(numQuestionSet++, questionSet);
    }
    
    public static TFQuestionContainer getInstance(Context c) {
        if (sTFQuestionContainer == null) {
            sTFQuestionContainer = new TFQuestionContainer(c.getApplicationContext());
        }
        return sTFQuestionContainer;
    }
    
    public SparseArray<TFQuestion[]> getQuestionSets() {
        return mQuestions;
    }
    
    public int getNumQuestionsPerSet() {
        return mQuestionsPerSet;
    }

}
