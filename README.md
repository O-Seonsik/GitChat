(2019 학년도 2학기<br>
가톨릭대학교 컴퓨터정보공학부 소프트웨어공학 수업 프로젝트 결과물 입니다.<br>
Github를 이용해 회원가입 되어있는 사람과 아이디를 연동하여 커밋수를 체크하고,<br>
대화의 양을 확인하여 기여도를 평가하는 컴퓨터공학 팀프로젝트를 위한 채팅 프로그램입니다.<br><br>

백앤드 : 컴공16 오선식<br>
프론트 : 컴공16 손영락<br>
디자인 : 컴공17 주수빈<br>

백앤드와, 프론트는 사실상 협업하여 구분없이 개발을 진행하였습니다.<br><br>

기술스택<br>
JAVA(언어), JAVA Socket(TPC/IP), Selenium(깃 크롤링을 위해), Swing(GUI), mySql(DB)<br><br>

DB구조<br>
<ul>All_Member
  <li>id(auto_increment)</li>
  <li>mem_ID(아이디)</li>
  <li>mem_PW(비밀번호)</li>
  <li>name(이름)</li>
  <li>github_ID(깃허브 아이디)</li>
  <li>status(접속상태)</li>
</ul>

<ul>Chat_Info
  <li>Room(방 번호)</li>
  <li>Description(대화내용)</li>
  <li>Sender(발신자)</li>
  <li>Time(시간, 초까지)</li>
  <li>Day(년,월,일)</li>
</ul>

<ul>Github
  <li>git_id(깃 아이디)</li>
  <li>commit(커밋 수)</li>
  <li>repo_id(대화방 id)</li>
</ul>

<ul>mem_friend_list
  <li>user_ID(유저 아이디)</li>
  <li>friend_ID(유저의 친구 아이디)</li>
</ul>

<ul>Room
  <li>roomNum(방 번호 auto_inclement)</li>
  <li>title(방 제목)</li>
  <li>repository(깃허브 레파지토리 주소)</li>
</ul>

<ul>Room_Member
  <li>memID(userID)</li>
  <li>roomNum(참가중인 방 id)</li>
</ul>

README FILE made by.오선식
