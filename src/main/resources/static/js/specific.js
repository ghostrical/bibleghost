console.log("specific.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const inputTitle = document.getElementById("input-title");
    const inputChapter = document.getElementById("input-chapter");
    const inputVerse = document.getElementById("input-verse");
    const btnCheck = document.getElementById("btn-check");
    const responseText = document.getElementById("response-text");

    btnCheck.addEventListener("click", function() {
        const title = inputTitle.value.trim();
        const chapter = inputChapter.value.trim();
        const verse = inputVerse.value.trim();

        if (!title || !chapter || !verse) {
            alert("모든 입력값을 채워주세요.");
            return;
        }


        const url = `http://localhost:8080/content/specific/${title}/${chapter}/${verse}`;

        fetch(url)
            .then(response => response.text()) // String으로 받기
            .then(data => {
                responseText.value = data; // 결과 textarea에 표시
            })
            .catch(err => {
                console.error("요청 실패:", err);
                responseText.value = "데이터 로딩 실패";
            });
    });
});