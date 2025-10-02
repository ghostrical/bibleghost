console.log("verses.js 확인");

document.addEventListener("DOMContentLoaded", function() {
    const displayDiv = document.getElementById("chapter-display");
    const tbody = document.getElementById("data-table");


    const currentUrl = window.location.href;
    const match = currentUrl.match(/\/thisTitle\/([^\/]+)\/thisChapter\/(\d+)/);
    let title = "";
    let chapter = "";
    if (match) {
        title = decodeURIComponent(match[1]);
        chapter = parseInt(match[2]);
    }


    displayDiv.textContent = `선택된 성경코드: ${title}, 장: ${chapter}`;


    fetch(`http://localhost:8080/content/title/${title}/chapter/${chapter}`)
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
            console.error("데이터 로딩 실패:", err);
        });
});