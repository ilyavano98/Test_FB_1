package com.example.demo.controllers;

import com.example.demo.models.Question;
import com.example.demo.models.QuestionType;
import com.example.demo.models.Theme;
import com.example.demo.models.UserResponse;
import com.example.demo.repositoryes.QuestionsRepository;
import com.example.demo.repositoryes.ResponsesRepository;
import com.example.demo.repositoryes.ThemeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    private final QuestionsRepository questionsRepository;
    private final ResponsesRepository responsesRepository;
    private final ThemeRepository themeRepository;

    public MainController(QuestionsRepository questionsRepository, ResponsesRepository responsesRepository,
                          ThemeRepository themeRepository) {
        this.questionsRepository = questionsRepository;
        this.responsesRepository = responsesRepository;
        this.themeRepository = themeRepository;
    }
    @Setter
    @Getter
    public static class PageData
    {
        private Question question;
        private UserResponse userResponse;
        private List<Theme> theme;
    }
    @RequestMapping(value = {"/home" }, method = RequestMethod.GET)
    public String loadingHome(Model model)
    {
//        Theme theme = new Theme();
//        theme.setText("Hello world");
//
//        Question question = new Question();
//        question.setTheme(theme);
//        question.setQuestionType(QuestionType.CHECK_BOX);
//        question.setText("This is almost question");
//
//        List<Question> questions = new ArrayList<>();
//        questions.add(question);
//
//
//        List<ResponseOption> options = new ArrayList<>();
//        ResponseOption responseOption = new ResponseOption();
//        options.add(responseOption);
//        responseOption.setText("This is question Y/N ?");
//
//        responseOption.setQuestion(question);
//        theme.setQuestions(questions);
//        question.setOptions(options);
//
//        questionsRepository.save(question);
//
//        Optional<Question> q = questionsRepository.findById(Long.valueOf(1));
//
//
//        UserResponse userResponse = new UserResponse();
//        userResponse.setText("I'm a new user");
//        QuestionResponse questionResponse = new QuestionResponse();
//        questionResponse.setQuestion(question);
//        questionResponse.setText("This is my question, this is ass");
//        questionResponse.setOptions(options);
//        List<QuestionResponse> responses = new ArrayList<>();
//        responses.add(questionResponse);
//        userResponse.setQuestions(responses);
//
//        responsesRepository.save(userResponse);
//
//        UserResponse u = responsesRepository.findByText("I'm a new user");

        List<Theme> theme = themeRepository.findAll();
//        PageData pageData = new PageData();
//        pageData.setTheme(theme);
        model.addAttribute("theme", theme);
        return "home";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String loadHome(Model model)
    {
        List<Theme> theme = themeRepository.findAll();
        model.addAttribute("theme", theme);
        return "home";
    }

    @RequestMapping(value = {"/poll/{id}"}, method = RequestMethod.GET)
    public String loadPoll(@PathVariable(value = "id") long id, Model model)
    {
        List<Question> poll = questionsRepository.findByTheme_id(id);
//        QuestionType questionType = new QuestionType();
        model.addAttribute("poll", poll);
        model.addAttribute("id", id);
//        model.addAttribute("questionType", questionType);
        return "poll";
    }

    @RequestMapping(value = {"/createPoll"}, method = RequestMethod.GET)
    public String createPollThemeOpen(Model model)
    {
        return "createPoll";
    }

    @RequestMapping(value = {"/admin" }, method = RequestMethod.GET)
    public String loadAdmin(@RequestParam String password)
    {
        System.out.println(password);
        String true_pass = "12345";
        if(password.equals(true_pass))
        {
            return "admin";
        }
        else
        {
            return "redirect:/home";
        }
    }

}
