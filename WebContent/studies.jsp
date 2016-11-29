
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp?user=Hello,Kim" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >

<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>     
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="study" items="${studies}">
        <tr>
            <%-- First study details --%>
            <td>${study.studyName }</td>
            <td>${study.requestedParticipants }</td>
            <td>${study.numofParticipants }</td>
            <td>
            
            
           <form action ="study?action=<c:if test="${study.status == 'start'}">stop</c:if><c:if test="${study.status == 'stop'}">start</c:if>&studycode=${study.studyCode}" method = post>
            <button type="submit" class="btn btn-primary">
            <c:if test="${study.status == 'start'}">
            <c:out value="stop"></c:out>
            </c:if>
            <c:if test="${study.status == 'stop'}">
            <c:out value="start"></c:out>
            </c:if>
            </button>
            </form>
            </td>
            
            <%-- Code to display edit page --%>
            <td><form action="study?studycode=${study.studyCode }" method="post">
             <input type="hidden" name="action" value="edit">
                    <button type="submit" class="btn btn-primary">Edit</button></form></td>

        </tr>
        </c:forEach>
    </table>
</div>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>