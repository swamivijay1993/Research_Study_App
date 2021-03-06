
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    <ul>
        <li>Coins (<span class="count">${coins}</span>) </li>
         <li>Participants (<span class="count">${numParticipants}</span>) </li>
        <li>Participation (<span class="count">${numParticipation }</span>) </li>
        <li><br></li>
        <li><a href="user?action=main">Home</a></li>
        <li><a href="study?action=participate">Participate</a></li>
        <li><a href="study?action=studies">My Studies</a></li>
        <li><a href="recommend.jsp?user=Hello,Kim">Recommend</a></li>
        <li><a href="contact.jsp?user=Hello,Kim">Contact</a></li>
    </ul>
</nav>
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="images/${study.imageURL }" class="img-responsive" height="250" width="250" alt="Tree"/>

<%--Code to rating the Question --%>
    <p class="text-left">${study.question}(3 strongly agree - 5 strongly disagree)</p>

        <form action="study?studycode=${study.studyCode}" method="post">
        <input type= "hidden" name = "action" value ="answer"/>
        <input type= "hidden" name = "questionCode" value ="${study.questionCode }"/>
       <c:forEach items="${study.answers}" var = "answer">
       		 <div class="radio">
            <input type="radio" name="number" value="${answer}" required> ${answer}
            </div>
       </c:forEach>
       
    
<%-- Code to submit the Rating  --%>
    
         <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
         </div>
            </div>
            <br/><br/><br/>   
        </form>
        
    
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>