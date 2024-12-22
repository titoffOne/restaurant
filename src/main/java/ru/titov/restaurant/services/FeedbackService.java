package ru.titov.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.titov.restaurant.repositories.FeedbackRepository;
import ru.titov.restaurant.model.Feedback;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }

}
