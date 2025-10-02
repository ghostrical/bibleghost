// main.js 확인용 로그
console.log("main.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    // HTML 내 JS 확인 메시지
    const div = document.getElementById("js-check");
    div.textContent = "main.js 확인";

    // 서버에서 /content/title로 GET 요청을 보내 DTO List를 가져오기
    fetch("http://localhost:8080/content/title")
        .then(response => response.json()) // JSON으로 변환
        .then(data => {
            const tbody = document.getElementById("data-table");
            tbody.innerHTML = ""; // 기존 내용 초기화

            // 서버에서 받은 DTO List를 테이블에 동적으로 추가
            data.forEach(item => {
                const tr = document.createElement("tr");

                // title → 성경코드
                const tdTitle = document.createElement("td");
                tdTitle.textContent = item.title;
                tr.appendChild(tdTitle);

                // titleNm → 성경전서
                const tdTitleNm = document.createElement("td");
                tdTitleNm.textContent = item.titleNm;
                tr.appendChild(tdTitleNm);

                tbody.appendChild(tr);
            });
        })
        .catch(err => {
            // 에러 발생 시 콘솔에 출력
            console.error("데이터 로딩 실패:", err);
        });
});