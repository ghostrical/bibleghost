console.log("boardupdate.js 확인");

document.addEventListener("DOMContentLoaded", function() {

    const pathParts = window.location.pathname.split("/");
    const boardPk = pathParts[pathParts.length - 1];


    const titleInput = document.getElementById("title");
    const writerInput = document.getElementById("writer");
    const sourceInput = document.getElementById("source");


    document.getElementById("listBtn").addEventListener("click", function() {
        window.location.href = "http://localhost:8080/freeBoard/board";
    });


    fetch(`http://localhost:8080/freeBoard/${boardPk}`)
        .then(response => response.json())
        .then(data => {
            titleInput.value = data.title;
            writerInput.value = data.writer;
            sourceInput.value = data.source;
        })
        .catch(err => {
            console.error("데이터 로딩 실패:", err);
            alert("데이터 로딩 실패");
        });


    document.getElementById("updateForm").addEventListener("submit", function(event) {
        event.preventDefault();


        const now = new Date();
        const pad = (num) => num.toString().padStart(2, "0");
        const nowStr = `${now.getFullYear()}${pad(now.getMonth()+1)}${pad(now.getDate())}${pad(now.getHours())}${pad(now.getMinutes())}${pad(now.getSeconds())}`;

        const bodyData = {
            title: titleInput.value,
            writer: writerInput.value,
            source: sourceInput.value,
            createDt: nowStr,
            updateDt: nowStr
        };

        fetch(`http://localhost:8080/freeBoard/${boardPk}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(bodyData)
        })
        .then(response => {
            if(response.ok) {

                window.location.href = "http://localhost:8080/freeBoard/board";
            } else {
                alert("수정 실패: " + response.status);
            }
        })
        .catch(err => {
            console.error("PATCH 요청 실패:", err);
            alert("수정 중 오류 발생");
        });
    });
});