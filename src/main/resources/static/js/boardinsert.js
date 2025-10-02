console.log("boardinsert.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const submitBtn = document.getElementById("submitBtn");

    submitBtn.addEventListener("click", function() {

        const boardPk = document.getElementById("boardPk").value;
        const title = document.getElementById("title").value;
        const writer = document.getElementById("writer").value;
        const source = document.getElementById("source").value;


        const now = new Date();
        const pad = (n) => n.toString().padStart(2, '0');
        const createDt = `${now.getFullYear()}${pad(now.getMonth()+1)}${pad(now.getDate())}${pad(now.getHours())}${pad(now.getMinutes())}${pad(now.getSeconds())}`;
        const updateDt = createDt;


        const bodyData = {
            boardPk: parseInt(boardPk),
            title: title,
            writer: writer,
            source: source,
            createDt: createDt,
            updateDt: updateDt
        };

        fetch("http://localhost:8080/freeBoard", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bodyData)
        })
        .then(response => {
            if (response.ok) {

                window.location.href = "http://localhost:8080/freeBoard/board";
            } else {
                alert("입력 실패: " + response.status);
            }
        })
        .catch(err => {
            console.error("POST 요청 실패:", err);
            alert("입력 중 오류 발생");
        });
    });
});