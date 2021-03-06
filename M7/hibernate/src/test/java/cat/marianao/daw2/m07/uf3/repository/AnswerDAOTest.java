package cat.marianao.daw2.m07.uf3.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cat.marianao.daw2.m07.uf3.config.HibernateH2DatabaseConfig;
import cat.marianao.daw2.m07.uf3.config.ServicesConfig;
import cat.marianao.daw2.m07.uf3.domain.Answer;
import cat.marianao.daw2.m07.uf3.domain.Question;
import cat.marianao.daw2.m07.uf3.domain.User;
import cat.marianao.daw2.m07.uf3.utils.Mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServicesConfig.class, HibernateH2DatabaseConfig.class })
public class AnswerDAOTest {
    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void saveOk() {
        /* Setup */
        Timestamp currentTime = new Timestamp(new Date().getTime());

        /* userQ, which did the question */
        User userQ = Mock.getUser(null, "jack");
        userQ.setCreatedOn(currentTime);
        userDAO.save(userQ);

        Question question = new Question();
        question.setText("This is a question");
        question.setUser(userQ);
        questionDAO.save(question);

        /* userA, which does the answer related to the question before */
        String username = "wilson";

        User userA = Mock.getUser(null, username);
        userA.setCreatedOn(currentTime);
        userDAO.save(userA);

        Answer answer = new Answer();
        answer.setText("This is an answer");
        answer.setUser(userA);

        /* Previous verification */
        assertNull(answer.getAnswerId());

        /* Test */
        answerDAO.save(answer);

        /* Verification */
        assertNotNull(answer.getAnswerId());

        Answer answerFromDb = answerDAO.getById(answer.getAnswerId());

        assertEquals(answer.getAnswerId(), answerFromDb.getAnswerId());
        assertEquals(username, answerFromDb.getUser().getUsername());
        assertEquals(currentTime, answerFromDb.getUser().getCreatedOn());
    }
}
