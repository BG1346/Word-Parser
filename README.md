단어 분석기
==========

### 개요

```
자료구조
Dataset의 경로는 상대경로 '소스파일 상위 폴더의 DataSet'입니다.
dictionary : term과 postingslist를 저장한 hashmap
docId : 문서번호와 문서본문을 저장한 hashmap
freq : 단어의 빈도를 저장한 hashmap
list : 연속된 postingslist를 구현하는 linkedlist
문서본문 출력서식 지정 및 dictionary함수 호출문 : line 50 ~ 93
빈도수의 최대, 최소값 찾기 : line 100 ~ 110
문서 전체 통계 : line 113 ~ 118
질의문 출력 함수 : line 121 ~ 151
단어 전처리 및 dictionary에 추가 : line 156 ~ 192 parsing, add 메서드
```


### 시범출력

```
전체 단어의 수 : 163776 개
총 문서의 수 : 1552 개
문서 당 평균 단어 수 : 105.53 개
문서에 포함된 단어 수 중 가장 큰 수 : 9447 개
문서에 포함된 단어 수 중 가장 작은 수 : 1 개

********질의기*************
*************************
* 특정단어 찾기  : 1 *
* 문서 본문 출력하기  : 2 *
* 그만하기  : 3 *
*************************
입력 : 1
단어 입력 : color
[13, 38, 84, 276, 281, 281, 287, 295, 348, 353, 354, 354, 389, 400, 430, 444, 458, 486, 515, 554, 622, 625, 627, 678, 693, 711, 803, 861, 890, 910, 924, 959, 1023, 1049, 1061, 1094, 1100, 1120, 1163, 1168, 1205, 1206, 1209, 1248, 1259, 1375, 1388, 1416, 1437, 1456, 1548, 1548]

********질의기*************
*************************
* 특정단어 찾기  : 1 *
* 문서 본문 출력하기  : 2 *
* 그만하기  : 3 *
*************************
입력 : 2
문서 번호 입력 : 13
Documnet : 2007_acura_rl
Date     : 04/17/2007
Author   : Jim Cogan
Text     : This car is just an incredible vehicle, very high tech, but easy to figure out. The stereo is awesome, love the subwoofer. Controls are very accessible. Voice command system works great. Smooth ride, don't even know you are going as fast as you are (yikes). Gas mileage not as good as I hoped, but it is all wheel drive. This car has many options that you would pay 10k more for vehicle. handling is incredible and steering response is nice
Favorite : Can connect iPod into stereo system and stereo is awesome. Color of lakeshore silver is beautiful. 

********질의기*************
*************************
* 특정단어 찾기  : 1 *
* 문서 본문 출력하기  : 2 *
* 그만하기  : 3 *
*************************
입력 : 3

Process finished with exit code 0
```
