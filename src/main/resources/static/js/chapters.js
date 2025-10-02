console.log("chapters.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const displayDiv = document.getElementById("title-display");
    const tbody = document.getElementById("data-table");


    const currentUrl = window.location.href;
    const match = currentUrl.match(/\/thisTitle\/([^\/?#]+)/);
    let title = "";
    if (match && match[1]) {
        title = decodeURIComponent(match[1]);
    }


    displayDiv.textContent = "선택된 성경코드: " + title;


    fetch(`http://localhost:8080/content/title/${title}`)
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = "";

            data.forEach(item => {
                const tr = document.createElement("tr");


                const tdTitle = document.createElement("td");
                tdTitle.textContent = item.title;
                tr.appendChild(tdTitle);


                const tdTitleNm = document.createElement("td");
                tdTitleNm.textContent = item.titleNm;
                tr.appendChild(tdTitleNm);


                const tdChapter = document.createElement("td");
                const aChapter = document.createElement("a");
                aChapter.textContent = item.chapter;
                aChapter.href = `http://localhost:8080/content/thisTitle/${item.title}/thisChapter/${item.chapter}`;
                tdChapter.appendChild(aChapter);
                tr.appendChild(tdChapter);


                const tdChapterNm = document.createElement("td");
                tdChapterNm.textContent = item.chapterNm;
                tr.appendChild(tdChapterNm);

                tbody.appendChild(tr);
            });
        })
        .catch(err => {
            console.error("데이터 로딩 실패:", err);
        });
});