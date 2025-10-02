console.log("boardmain.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const tbody = document.getElementById("data-table");


    fetch("http://localhost:8080/freeBoard")
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = ""; // 기존 내용 초기화

            data.forEach(item => {
                const tr = document.createElement("tr");


                const tdPk = document.createElement("td");
                tdPk.textContent = item.boardPk;
                tr.appendChild(tdPk);


                const tdTitle = document.createElement("td");
                const link = document.createElement("a");
                link.href = `http://localhost:8080/freeBoard/boardDetail/${item.boardPk}`;
                link.textContent = item.title;
                tdTitle.appendChild(link);
                tr.appendChild(tdTitle);


                const tdWriter = document.createElement("td");
                tdWriter.textContent = item.writer;
                tr.appendChild(tdWriter);


                const tdCreate = document.createElement("td");
                tdCreate.textContent = item.createDt;
                tr.appendChild(tdCreate);


                const tdUpdate = document.createElement("td");
                tdUpdate.textContent = item.updateDt;
                tr.appendChild(tdUpdate);

                tbody.appendChild(tr);
            });
        })
        .catch(err => {
            console.error("데이터 로딩 실패:", err);
            tbody.innerHTML = "<tr><td colspan='5'>데이터 로딩 실패</td></tr>";
        });
});