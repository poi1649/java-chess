# java-chess
## 1단계 - 체스판 초기화
### 기능 요구 사항
- **체스판 생성**
- [x] 체스판의 세로는 아래부터 위로 1 ~ 8로 구현한다
- [x] 체스판의 가로는 왼쪽부터 a ~ h로 구현한다
- **체스 말 배치**
- [x] 체스 말의 흑, 백은 대문자와 소문자로 구분한다
- [x] 체스 말의 종류는 폰, 룩, 나이트, 비숍, 퀸, 킹이 있다
- [x] 체스 말은 위치를 갖는다 (각자 초기 위치를 갖고있다)
  - 8: RNBQKBNR 배치
  - 7: PPPPPPPP 배치
  - 6~3: ........ 으로 배치
  - 2: pppppppp 배치
  - 1: rnbqkbnr 배치
- **입력**
- [x] 게임 시작 명령어는 start, 종료는 end 입력이 올바르지 않다면 예외 처리한다 

## 2단계 - 말 이동
### 기능 요구 사항
- **체스 말**
- [x] 이동 가능한지 검사
  - [x] 이동 위치에 같은 색의 체스 말이 있다면 이동할 수 없다 <- 체스판의 책임
  - [x] 체스 말 마다 가능한 이동이 다르다 <- Piece 마다 다르게 가져온다
    - [x] 이동 위치를 입력 받아 가능한 이동인지 계산하고 판단한다
- [x] 경로를 반환한다
- **체스판**
- [x] 체스 말을 움직인다
- [x] 체스 말들의 위치를 갖고 있다
  - [x] 선택한 위치에 체스 말이 없다면 예외 처리한다
  - [x] 체스 말을 이동할 순서가 다르다면 예외 처리한다
    - [x] 이동은 색을 번갈아 가며 한다 -> controller
- [x] 체스 말이 반환한 경로 안에 다른 체스 말이 있는지 검사
  - [x] 경로 안에 다른 체스 말이 있다면 이동할 수 없다 <- Path
- **입력**
- [x] move source 위치 target 위치을 실행해 이동한다

## 3단계 - 승패 및 점수
### 기능 요구 사항
- [x] King 이 잡혔을 때 게임을 종료해야 한다
  - [x] 체스판에서 잡힌 말을 반환
  - [x] 체스 말에서 해당 말의 타입을 반환
- [x] status 명령어를 입력하면 점수를 출력
  - [x] 점수를 계산하는 ScoreCalculator 클래스
    - [x] 같은 File 에 있는 Pawn 은 0.5점을 준다

## 4단계 - DB 적용
### 기능 요구 사항
- **DB 연결 전 기능**
- [x] end 명령어를 입력하면 각자의 점수와 승리 팀을 출력한다
  - [x] 점수를 계산해서 점수가 높은 팀이 승리한다
- **DB 연결**
- [ ] 애플리케이션을 재시작하더라도 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다
  - [ ] 이어하기
    - 이전에 진행하던 게임이 있다면 이어서 게임을 시작한다
    - 이전에 진행하던 게임이 없다면 새로운 게임을 시작한다
  - [ ] 새로하기
    - 이전에 진행하던 게임이 있어도 새로운 게임을 시작한다

- chessGame(id를 갖고있는<-방 번호가 됨)을 따로 저장, Turn 을 저장할 필요가 있음, 게임의 진행 상태를 저장할 필요가 있음

- [ ] 피스를 이동할 때 마다 피스 테이블을 업데이트한다
- [ ] 체스 게임을 저장할 수 있다
- [ ] 체스 게임을 찾을 수 있다
- [ ] 체스 게임을 삭제할 수 있다

```
CREATE TABLE chess_games (
    id BIGINT NOT NULL AUTO_INCREMENT,
    current_turn VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pieces (
    id BIGINT NOT NULL AUTO_INCREMENT,
    game_id BIGINT NOT NULL,
    piece_file INT NOT NULL,
    piece_rank INT NOT NULL,
    color VARCHAR(16) NOT NULL,
    type VARCHAR(16) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES games(id)
);

```
