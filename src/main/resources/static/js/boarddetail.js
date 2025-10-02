console.log("boarddetail.js 확인");

document.addEventListener("DOMContentLoaded", function() {

    const pathParts = window.location.pathname.split("/");
    const boardPk = pathParts[pathParts.length - 1];


    fetch(`http://localhost:8080/freeBoard/${boardPk}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("boardPk").textContent = data.boardPk;
            document.getElementById("title").textContent = data.title;
            document.getElementById("writer").textContent = data.writer;
            document.getElementById("source").textContent = data.source;
            document.getElementById("createDt").textContent = data.createDt;
            document.getElementById("updateDt").textContent = data.updateDt;
        })
        .catch(err => {
            console.error("데이터 로딩 실패:", err);
            alert("데이터 로딩 실패");
        });


    document.getElementById("listBtn").addEventListener("click", function() {
        window.location.href = "http://localhost:8080/freeBoard/board";
    });


    document.getElementById("updateBtn").addEventListener("click", function() {
        window.location.href = `http://localhost:8080/freeBoard/update/${boardPk}`;
    });


    document.getElementById("deleteBtn").addEventListener("click", function() {
        if(confirm("정말 삭제하시겠습니까?")) {
            fetch(`http://localhost:8080/freeBoard/${boardPk}`, {
                method: "DELETE"
            })
            .then(response => {
                if(response.ok) {

                    window.location.href = "http://localhost:8080/freeBoard/board";
                } else {
                    alert("삭제 실패: " + response.status);
                }
            })
            .catch(err => {
                console.error("삭제 요청 실패:", err);
                alert("삭제 중 오류 발생");
            });
        }
    });
});