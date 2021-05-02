/**
 *
 */
package fr.gamedev.question.web.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author djer1
 *
 */
@Controller
public class QuestionController {

	QuestionService questionService;

	public QuestionController(@Autowired QuestionService theQuestionService) {
		this.questionService = theQuestionService;
	}

	@GetMapping("/question")
	public String showQuestion(Model model) {

		model.addAttribute("question", questionService.getQuestion("bob"));
		return "question";
	}

	@PostMapping("/question")
	public String validateResponse(Question question, Model model) {

		model.addAttribute("answer", questionService.validateResponse(question));

		return "validateAnswer";

	}

}
