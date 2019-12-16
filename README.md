(2019 학년도 2학기
가톨릭대학교 컴퓨터정보공학부 소프트웨어공학 수업
프로젝트 결과물 입니다.
Github를 이용해 회원가입 되어있는 사람과 아이디를 연동하여
커밋수를 체크하고, 대화의 양을 확인하여 기여도를 평가하는
컴퓨터공학 팀프로젝트를 위한 채팅 프로그램입니다.

백앤드 : 컴공16 오선식
프론트 : 컴공16 손영락
디자인 : 컴공17 주수빈

백앤드와, 프론트는 사실상 협업하여 구분없이 개발을 진행하였습니다.

기술스택
JAVA(언어), JAVA Socket(TPC/IP), Selenium(깃 크롤링을 위해), Swing(GUI), mySql(DB)

DB구조
All_Member
  id(auto_increment) mem_ID(아이디) mem_PW(비밀번호) name(이름) github_ID(깃허브 아이디) status(접속상태)
Chat_Info
  Room(방 번호) Description(대화내용) Sender(발신자) Time(시간, 초까지) Day(년,월,일)
Github
  git_id(깃 아이디) commit(커밋 수) repo_id(대화방 id)
mem_friend_list
  user_ID(유저 아이디) friend_ID(유저의 친구 아이디)
Room
  roomNum(방 번호 auto_inclement) title(방 제목) repository(깃허브 레파지토리 주소)
Room_Member
  memID(userID) roomNum(참가중인 방 id)

README FILE made by.오선식
