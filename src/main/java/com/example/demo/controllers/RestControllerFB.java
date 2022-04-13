package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositoryes.QuestionsRepository;
import com.example.demo.repositoryes.ResponsesRepository;
import com.example.demo.repositoryes.ThemeRepository;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RestControllerFB {
    private final QuestionsRepository questionsRepository;
    private final ResponsesRepository responsesRepository;
    private final ThemeRepository themeRepository;

    public RestControllerFB(QuestionsRepository questionsRepository, ResponsesRepository responsesRepository,
                          ThemeRepository themeRepository) {
        this.questionsRepository = questionsRepository;
        this.responsesRepository = responsesRepository;
        this.themeRepository = themeRepository;
    }

    @ResponseBody
    @RequestMapping(value="/createPoll",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public String createPollTheme(@RequestBody RequestJson reguest, Model model)
    {

        //RequestJson.QuestionJson g = reguest.getListQuestion().get(1);
//        System.out.println(reguest.getListQuestion().size());
        Map<Long, RequestJson.QuestionJson> listQuestions = reguest.getListQuestion();
        Theme theme = new Theme();
        theme.setText(reguest.getTheme());
        for ( int i = 0; i < listQuestions.size(); i++) {
            RequestJson.QuestionJson g = reguest.getListQuestion().get(i);
            Question question = new Question();
            question.setTheme(theme);
            if(g.getSelType().equals("1"))
            {
                question.setQuestionType(QuestionType.TEXT);
            }
            else if(g.getSelType().equals("2"))
            {
                question.setQuestionType(QuestionType.RADIO_BUTTON);
            }
            else
            {
                question.setQuestionType(QuestionType.CHECK_BOX);
            }
            question.setText(g.getTxtQuestion());

            List<Question> questions = new ArrayList<>();
            questions.add(question);


            List<ResponseOption> options = new ArrayList<>();
            if(g.getSelType().equals("1") || g.getSelType().equals("2"))
            {
                String[] arVar = g.getVariant().split("\\|");
                for(int j=0; j < arVar.length; j++)
                {
                    ResponseOption responseOption = new ResponseOption();
                    responseOption.setText(arVar[j]);
                    responseOption.setQuestion(question);
                    options.add(responseOption);
                }
            }
            theme.setQuestions(questions);
            question.setOptions(options);

            questionsRepository.save(question);
        }

//        themeRepository.save(theme);
        List<Theme> themes = themeRepository.findAll();
        model.addAttribute("allThemes", themes);
        return "redirect: /admin";
    }

    @ResponseBody
    @RequestMapping(value="/saveResponse",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public String saveUserResponse(@RequestBody RequestJson reguest, Model model) {
        Optional<Theme> theme = themeRepository.findById(reguest.getId());
        UserResponse userResponse = new UserResponse();
        userResponse.setText("Илья");
        ArrayList<QuestionResponse> questionResponses = new ArrayList<>();
        for (Map.Entry<Long, RequestJson.QuestionJson> entry : reguest.getListQuestion().entrySet())
        {
            Optional<Question> question = questionsRepository.findById(entry.getKey());
            if(question.isPresent())
            {
                QuestionResponse questionResponse = new QuestionResponse();
                questionResponse.setQuestion(question.get());
                if (entry.getValue().getSelType().equals("0")) {
                    questionResponse.setText(entry.getValue().getVariant());
                } else {
                    String[] arVar = entry.getValue().getVariant().split(";");
                    List<ResponseOption> list = question.get().getOptions().stream().filter(q ->
                            Arrays.stream(arVar).anyMatch(str -> str.equals(q.getText()))
                    ).collect(Collectors.toList());
                    questionResponse.setOptions(list);
                }
                questionResponses.add(questionResponse);
            }
        }
        userResponse.setQuestions(questionResponses);
        responsesRepository.save(userResponse);
        return "redirect: /admin";
    }
}
