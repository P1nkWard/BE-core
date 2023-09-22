# BE-core
FoodCatch App - core

<br>

# Commit Convention

커밋 메시지 구조는 `제목` `본문` `꼬리말` 세가지 파트로 나누고 <br>
각 파트는 빈 줄로 구분한다

```
[GitMoji] [Type] : [Subject]

[Body]

[Footer] 
```

<br>

## Subject
**Gitmoji + Tag + 영어 커밋 메시지** 형식으로 작성한다 <br><br>

**태그**는 영어로 쓰며, 첫 문자는 대문자로 한다 <br>
**깃모지**는 이모지를 직접 사용하거나 `:이모지이름:` 의 형태로 사용할 수 있다 <br>
**커밋 메시지**는 동사(원형)을 가장 앞에 두고 첫글자는 대문자로 하여 간결하게 표기한다 <br><br>
`ex) git commit -m ":sparkles:Feat: Feature search API"` -> `✨Feat: Feature search API`
#### Tag
- Feat : 새로운 기능 추가
- Fix : 버그, 오류 수정
- Docs : 문서 수정
- Style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- Refactor : 코드 리펙토링
- Test : 테스트 코드, 리펙토링 테스트 코드 추가
- Chore : 빌드 업무 수정, 패키지 매니저 수정

#### Gitmoji
- ✨ `:sparkles:` 새로운 기능 추가, 구현
- 💄 `:lipstick:` UI, 스타일 관련 파일 추가 및 수정
- 🐛 `:bug:` 버그 수정
- ✏️ `:pencil2:` 단순 오타 수정
- 💬 `:speech_balloon:` 텍스트 또는 리터럴 추가 및 수정
- 🍱 `:bento:` asset 파일(이미지, 아이콘 등) 추가
- 📝 `:memo:` 문서 파일 추가 및 수정
- ♿️ `:wheelchair:` 웹 접근성 향상을 위한 코드 추가 및 수정
- 🩹 `:adhesive_bandage:` 단순한, critical하지 않은 이슈 수정
- 🚚 `:truck:` 파일, 경로, route를 옮기거나 이름 변경
- ♻️ `:recycle:` 코드 리팩토링
- 🔥 `:fire:` 삭제(파일, 코드)
- 🙈 `:see_no_evil:` gitignore 추가 및 수정

<br>

## Body
본문은 다음의 규칙을 지킨다
- 본문 내용은 양에 구애받지 않고 최대한 상세하게 작성한다
- 본문 내용은 어떻게 변경했는지 보다는 **무엇을, 왜** 변경했는지를 설명한다

<br>

## Footer
꼬리말은 `Optional`이며 `이슈 트래커 ID`를 작성할 때 사용한다 <br><br>
꼬리말은 `"category: #issue number"` 형식으로 사용하며 <br>
여러 개의 이슈 번호를 적을 때는 `쉼표 (,)` 로 구분한다 <br>

#### Category
- `Fixes` : 이슈 수정 중에 사용 (아직 해결되지 않은 경우)
- `Resolves` : 이슈가 해결 되었을 때 사용
- `Ref` : 참고할 이슈가 있을 때 사용
- `Related to` : 해당 커밋에 관련된 이슈 번호

`ex) Related to: #12, #34`

<br>

## Commit sample
```
git commit -m ":sparkles:Feat: Feature register API

SMS, 이메일 중복확인 API 개발

Resolves: #123
Ref: #456
Related to: #48, #45"
```
