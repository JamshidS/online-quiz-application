package com.quiz.service.impl;


import com.quiz.model.QuizMetaData;
import com.quiz.model.QuizQuestion;
import com.quiz.repository.QuizMetaDataRepository;
import com.quiz.repository.QuizQuestionRepository;

import com.quiz.model.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;

import java.util.List;

public class QuizServiceImpl implements QuizService {

// TODO: relations of classes are not implement in the function as it's not added column to table. I should fix it.

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizRepository quizRepository;
    private final QuizMetaDataRepository quizMetaDataRepository;

    public QuizServiceImpl(QuizQuestionRepository quizQuestionRepository, QuizRepository quizRepository, QuizMetaDataRepository quizMetaDataRepository) {
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizRepository = quizRepository;
        this.quizMetaDataRepository = quizMetaDataRepository;
    }

    // QuizMetaData operations:

    @Override
    public String addQuizQuestionToQuizMetaData(List<QuizMetaData> quizMetaDataList, int quizQuestionID) {
        try {
            QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);
            if (quizQuestion != null) {
                boolean trueFound = false;
                for (QuizMetaData quizMetaData : quizMetaDataList) {
                    if (quizMetaData.isCorrect()) {
                        if (trueFound) {
                            throw new RuntimeException("Only one correct answer is allowed in question.");
                        }
                        trueFound = true;
                    }
                    quizMetaData.setQuizQuestion(quizQuestion);
                    quizMetaDataRepository.save(quizMetaData);
                }
                return "QuizQuestion added to QuizMetaData successfully.";
            } else {
                throw new RuntimeException("QuizQuestion not found with ID: " + quizQuestionID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while adding QuizQuestion to QuizMetaData.";
        }
    }

    @Override
    public QuizMetaData getQuizMetaData(int quizMetaDataID) {
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if (quizMetaData != null) {
            return quizMetaData;
        } else {
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);

        }
    }

    @Override
    public List<QuizMetaData> getAllQuizMetaData() {
        List<QuizMetaData> allQuizMetaData = quizMetaDataRepository.getAllQuizMetaData();
        if (allQuizMetaData != null) {
            return allQuizMetaData;
        } else {
            throw new RuntimeException("QuizMetaData not found.");
        }
    }

    @Override
    public List<QuizMetaData> getAllQuizMetaDataWithQuizQuestionID(int quizQuestionID) {
        QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);
        if (quizQuestion != null) {
            return quizQuestion.getQuizMetaData();
        } else {
            throw new RuntimeException("QuizQuestion not found with ID: " + quizQuestionID);
        }
    }

    @Override
    public String updateQuizMetaData(int quizQuestionID, int quizMetaDataID, QuizMetaData updatedMetaData) {
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        QuizQuestion quizQuestion = quizQuestionRepository.getQuizQuestionByID(quizQuestionID);
        if (quizMetaData != null) {
            if (!quizMetaData.isCorrect() && updatedMetaData.isCorrect()) {
                for (QuizMetaData metaData : quizQuestion.getQuizMetaData()) {
                    if (metaData.isCorrect()) {
                        metaData.setCorrect(false);
                        quizMetaDataRepository.update(metaData, metaData.getId());
                    }
                }
                quizMetaDataRepository.update(updatedMetaData, quizMetaDataID);
                return "QuizMetaData successfully updated!";
            } else if (quizMetaData.isCorrect() && !updatedMetaData.isCorrect()) {
                throw new RuntimeException("Firstly you must assign the correct answer!");
            } else {
                quizMetaDataRepository.update(updatedMetaData, quizMetaDataID);
                return "QuizMetaData successfully updated!";
            }
        } else {
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);
        }
    }

    @Override
    public void deleteQuizMetaData(int quizMetaDataID) {
        QuizMetaData quizMetaData = quizMetaDataRepository.getQuizMetaDataByID(quizMetaDataID);
        if (quizMetaData != null) {
            quizMetaDataRepository.delete(quizMetaDataID);
        } else {
            throw new RuntimeException("QuizMetaData not found with ID: " + quizMetaDataID);
        }
    }

    @Override
    public String saveQuizMetaData(int quizMetaDataID) {
        return null;
    }

    // QuizQuestion operations:

    @Override
    public QuizQuestion getQuizQuestion(int id) {
        return null;
    }

    @Override
    public List<QuizQuestion> getAllQuizQuestion() {
        return null;
    }

    @Override
    public String updateQuizQuestion(Long questionId, String newQuestion) {
        QuizQuestion updateQuizQuestion = quizQuestionRepository.getQuizQuestionByID(Math.toIntExact(questionId));
        if (updateQuizQuestion != null) {
            quizQuestionRepository.updateQuizQuestion(questionId, newQuestion);
            return "Question updated successfully.";
        } else {
            throw new IllegalArgumentException("Question with ID " + questionId + " not found.");
        }
    }

    @Override
    public String deleteQuizQuestion(Long questionId) {
        if (quizQuestionRepository.getQuizQuestionByID(Math.toIntExact(questionId)) == null) {
            throw new IllegalArgumentException("Deleting quiz failed, no rows affected.");
        } else {
            quizQuestionRepository.deleteQuizQuestion(questionId);
            return "Quiz deleted successfully.";
        }
    }

    @Override
    public String addQuizQuestion(QuizQuestion quizQuestion) {
        if (quizQuestion != null) {
            quizQuestionRepository.addQuizQuestion(quizQuestion);
            return "Question added successfully.";
        } else {
            throw new IllegalArgumentException("quizQuestion parameter cannot be null");
        }
    }

    // Quiz operations:

    @Override
    public Quiz getQuiz(int id) {
        Quiz quiz = quizRepository.getById(id);
        if (quiz != null) {
            return quiz;
        } else {
            throw new RuntimeException("Quiz not found with ID: " + id);
        }
    }

    @Override
    public List<Quiz> getAllQuiz() {
        List<Quiz> allQuiz = quizRepository.getAll();
        if (allQuiz != null) {
            return allQuiz;
        } else {
            throw new RuntimeException("Quiz not found.");
        }
    }

    @Override
    public Quiz updateQuiz(int id, Quiz updatedQuiz) {
        Quiz quiz = quizRepository.getById(id);
        if (quiz != null) {
            quizRepository.update(quiz,id);
            return quiz;
        } else {
            throw new RuntimeException("Quiz not found with ID: " + id);
        }
    }

    @Override
    public void deleteQuiz(int id) {
        Quiz quiz = quizRepository.getById(id);
        if (quiz != null) {
            quizRepository.delete(id);
        } else {
            throw new RuntimeException("Quiz not found with ID: " + id);
        }
    }

    @Override
    public String saveQuiz(Quiz quiz) {
        if (quiz != null) {
            quizRepository.save(quiz);
            return "Quiz successfully saved.";
        } else {
            throw new RuntimeException("Quiz not found with ID: " + quiz.getId());
        }
    }

}




