console.log("keyword.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const inputKeyword = document.getElementById("input-keyword");
    const btnCheck = document.getElementById("btn-check");
    const tbody = document.getElementById("data-table");

    btnCheck.addEventListener("click", function() {
        const keyword = inputKeyword.value.trim();

        if (!keyword) {
            alert("Keyword를 입력해주세요.");
            return;
        }

        const url = `http://localhost:8080/content/keyWord/${encodeURIComponent(keyword)}`;

        fetch(url)
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
                    tdChapter.textContent = item.chapter;
                    tr.appendChild(tdChapter);

                    const tdChapterNm = document.createElement("td");
                    tdChapterNm.textContent = item.chapterNm;
                    tr.appendChild(tdChapterNm);

                    const tdVerse = document.createElement("td");
                    tdVerse.textContent = item.verse;
                    tr.appendChild(tdVerse);

                    const tdVerseNm = document.createElement("td");
                    tdVerseNm.textContent = item.verseNm;
                    tr.appendChild(tdVerseNm);

                    const tdObject = document.createElement("td");
                    tdObject.textContent = item.object;
                    tr.appendChild(tdObject);

                    tbody.appendChild(tr);
                });
            })
            .catch(err => {
                console.error("요청 실패:", err);
                tbody.innerHTML = "<tr><td colspan='7'>데이터 로딩 실패</td></tr>";
            });
    });
});