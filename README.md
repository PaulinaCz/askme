# askme

Online Question&Answer service, based on stackoverflow.com and quora.com platforms.

Functionality:
- Display all questions for non-authorised users.
- Register new user.
- Login user.
- Add/delete questions.
- Add/delete answers.
- Add comments.
- Rate answers.
- Dispaly filtered data for logged in users.

Technologies:
- Spring Boot, Hibernate, REST,
- JSON Web Token,
- Mockito, JUnit5,
- MySQL, H2(for integration tests)


SWAGGER UI FUNCTIONALITY TESTING:

- DISPLAY QUESTIONS FOR NON-REGISTERED USER
 ![](https://github.com/PaulinaCz/askme/blob/master/images/allQuestionsWithoutAuth.png)
 
 - DISPLAY COMMENTS FORBIDDEN FOR NON-REGISTERED USERS
 ![](https://github.com/PaulinaCz/askme/blob/master/images/commentsForbiddenWithoutAuth.png)
 
 - LOGIN (RETURNING JWT TOKEN)
 ![](https://github.com/PaulinaCz/askme/blob/master/images/login.png)
 
 - ASK QUESTION
 ![](https://github.com/PaulinaCz/askme/blob/master/images/askQuestion.png)
 
 - ADD ANSWER
 ![](https://github.com/PaulinaCz/askme/blob/master/images/addAnswer.png)
 
 - DELETE ANSWER
 ![](https://github.com/PaulinaCz/askme/blob/master/images/deleteMyAnswer.png)
 
 - INPUT DATA VALIDATION
 ![](https://github.com/PaulinaCz/askme/blob/master/images/inputDataValidation.png)
 
 - REGISTRATION - VALIDATE IF USER EXISTS
 ![](https://github.com/PaulinaCz/askme/blob/master/images/registerUserWithExistingUsername.png)
