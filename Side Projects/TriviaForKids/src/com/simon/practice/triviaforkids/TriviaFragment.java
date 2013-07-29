package com.simon.practice.triviaforkids;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TriviaFragment extends Fragment {
    
    /* Used to generate random set or question */
    private final Random rand = new Random();
    
    /* Widgets */
    private TextView mTFQuestion; // TextView that displays current true/false question
    private Button mTrueButton;
    private Button mFalseButton;
    
    /* Regarding question sets and questions */
    private SparseArray<TFQuestion[]> mQuestionSets; // Holds different sets of questions
    private TFQuestion[] mQuestions; // Holds current set of question being used
    private boolean[] mQuestionSetsUsed; // Indicates which sets have been used
    private boolean[] mQuestionsUsed; // Indicates which questions in the current set have been used
    private int mQuestionSetsRemaining; // Number of sets remaining
    private int mQuestionsRemaining; // Number of questions remaining
    private TFQuestion mCurrentTFQuestion; // Current true/false question being displayed

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Get question sets from TFQuestionContainer */
        mQuestionSets = TFQuestionContainer.getInstance(getActivity()).getQuestionSets();
        
        /* Set all sets as unused */
        mQuestionSetsUsed = new boolean[mQuestionSets.size()];
        
        /* Initialize number of sets remaining */
        mQuestionSetsRemaining = mQuestionSets.size();
        
        /* Get initial random set of questions */
        mQuestions = mQuestionSets.get(chooseRandomSet());
        
        /* Set all questions as unused */
        mQuestionsUsed = new boolean[mQuestions.length];
        
        /* Initialize number of questions remaining */
        mQuestionsRemaining = mQuestions.length;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trivia, container, false);
        
        mTFQuestion = (TextView)v.findViewById(R.id.tfQuestion);
        displayNextQuestion(); // Reminder: Set has already been chosen in onCreate(...)
        
        mTrueButton = (Button)v.findViewById(R.id.trueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                checkAnswer(true); // Compares answer to that of the question
                
                /* Display congratulatory screen if all questions answered, and reset proper variables */
                if (allQuestionsUsed()) {
                    displayEndScreen();
                    return;
                }
                displayNextQuestion(); // Display next question if there are questions remaining
            }
        });
        
        mFalseButton = (Button)v.findViewById(R.id.falseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                
                if (allQuestionsUsed()) {
                    displayEndScreen();
                    return;
                }
                displayNextQuestion();
            }
        });
        
        return v;
    }
    
    /* Checks user input correctness and displays a Toast accordingly */
    private void checkAnswer(boolean userPressedTrue) {
        int toastResId;
        
        if (userPressedTrue == mCurrentTFQuestion.isTrue()) {
            toastResId = R.string.correctToast;
        }
        else {
            toastResId = R.string.incorrectToast;
        }
        Toast.makeText(getActivity(), toastResId, Toast.LENGTH_SHORT).show();
    }
    
    /* Displays the next random question that has not yet been used */
    private void displayNextQuestion() {
        mTFQuestion.setText(chooseRandomQuestion().getQuestion());
    }
    
    /* Chooses unused random set of questions to use */
    private int chooseRandomSet() {
        int setNumber = rand.nextInt(mQuestionSets.size());
        
        while (checkSetNumberUsed(setNumber)) {
            setNumber = rand.nextInt(mQuestionSets.size());
        }
        markSetUsed(setNumber);
        mQuestionSetsRemaining--;
        return setNumber;
    }
    
    /* Chooses unused random question to use */
    private TFQuestion chooseRandomQuestion() {
        int questionNumber = rand.nextInt(mQuestions.length);
        
        while (checkQuestionNumberUsed(questionNumber)) {
            questionNumber = rand.nextInt(mQuestions.length);
        }
        mCurrentTFQuestion = mQuestions[questionNumber]; // Set new current question
        markQuestionUsed(questionNumber);
        mQuestionsRemaining--;
        return mCurrentTFQuestion;
    }
    
    /* Marks set as used */
    private void markSetUsed(int setNumber) {
        mQuestionSetsUsed[setNumber] = true;
    }
    
    /* Marks question as used */
    private void markQuestionUsed(int questionNumber) {
        mQuestionsUsed[questionNumber] = true;
    }
    
    /* Checks if set number chosen is already used */
    private boolean checkSetNumberUsed(int currentSetNumber) {
        return mQuestionSetsUsed[currentSetNumber];
    }
    
    /* Checks if question number chosen is already used */
    private boolean checkQuestionNumberUsed(int currentQuestionNumber) {
        return mQuestionsUsed[currentQuestionNumber];
    }
    
    /* Checks whether all sets have been used */
    private boolean allSetsUsed() {
        return mQuestionSetsRemaining == 0;
    }
    
    /* Checks whether all questions in a set have been used */
    private boolean allQuestionsUsed() {
        return mQuestionsRemaining == 0;
    }
    
    /* Display congratulatory screen */
    private void displayEndScreen() {
        
    }

}
