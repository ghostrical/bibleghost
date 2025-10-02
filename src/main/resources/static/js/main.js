console.log("main.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const div = document.getElementById("js-check");
    div.textContent = "main.js 확인";


    fetch("http://localhost:8080/content/title")
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById("data-table");
            tbody.innerHTML = "";

            data.forEach(item => {
                const tr = document.createElement("tr");


                const tdTitle = document.createElement("td");
                const a = document.createElement("a");
                a.textContent = item.title;

                a.href = `http://localhost:8080/content/thisTitle/${item.title}`;
                tdTitle.appendChild(a);
                tr.appendChild(tdTitle);


                const tdTitleNm = document.createElement("td");
                tdTitleNm.textContent = item.titleNm;
                tr.appendChild(tdTitleNm);

                tbody.appendChild(tr);
            });
        })
        .catch(err => {
            console.error("데이터 로딩 실패:", err);
        });
});